package com.marvel.dingdangcat.service.impl;

import com.marvel.dingdangcat.constant.DingTaskApplyStatusEnum;
import com.marvel.dingdangcat.domain.ding.DingTask;
import com.marvel.dingdangcat.domain.ding.DingTaskApply;
import com.marvel.dingdangcat.domain.ding.DingTaskApplyStaff;
import com.marvel.dingdangcat.domain.view.ApplyDingTaskVo;
import com.marvel.dingdangcat.domain.view.LoginInfoVo;
import com.marvel.dingdangcat.domain.view.TDingTaskRequest;
import com.marvel.dingdangcat.helper.DingHelper;
import com.marvel.dingdangcat.mapper.ding.DingTaskApplyMapper;
import com.marvel.dingdangcat.mapper.ding.DingTaskApplyStaffMapper;
import com.marvel.dingdangcat.mapper.ding.DingTaskMapper;
import com.marvel.dingdangcat.service.DingService;
import com.marvel.dingdangcat.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marvel on 2019/9/30.
 */
@Service
public class DingServiceImpl implements DingService {

    private final DingTaskMapper dingTaskMapper;
    private final DingTaskApplyMapper dingTaskApplyMapper;
    private final DingTaskApplyStaffMapper dingTaskApplyStaffMapper;
    private final UserService userService;

    @Autowired
    public DingServiceImpl(
            DingTaskMapper dingTaskMapper,
            DingTaskApplyMapper dingTaskApplyMapper,
            DingTaskApplyStaffMapper dingTaskApplyStaffMapper,
            UserService userService) {
        this.dingTaskMapper = dingTaskMapper;
        this.dingTaskApplyMapper = dingTaskApplyMapper;
        this.dingTaskApplyStaffMapper = dingTaskApplyStaffMapper;
        this.userService = userService;
    }

    @Override
    public Long saveDingTask(TDingTaskRequest dingTaskRequest) {
        DingTask dingTask = new DingTask();
        BeanUtils.copyProperties(dingTaskRequest, dingTask);

        Integer applyStatus = DingHelper.calApplyStatus(LocalTime.now(), dingTask);
        dingTask.setApplyStatus(applyStatus);
        LoginInfoVo currentLoginInfo = userService.findCurrentLoginInfo();
        dingTask.setUpdatedBy(currentLoginInfo.getId());

        if (dingTask.getId() != null && dingTask.getId() > 0) {
            dingTaskMapper.update(dingTask);
        } else {
            dingTask.setCreatedBy(currentLoginInfo.getId());
            dingTaskMapper.create(dingTask);
        }
        return dingTask.getId();
    }

    @Override
    public void deleteDingTask(Long dingTaskId) {
        dingTaskMapper.delete(dingTaskId);
    }

    @Override
    public DingTask findDingTaskById(Long dingTaskId) {
        return dingTaskMapper.findById(dingTaskId);
    }

    @Override
    public List<DingTask> findAllDingTask() {
        return dingTaskMapper.findAll();
    }

    @Override
    public List<DingTask> findEnabledDingTasks() {
        return dingTaskMapper.findEnabled();
    }

    @Override
    public void applyDingTask(ApplyDingTaskVo applyDingTaskVo) {
        Long dingTaskId = applyDingTaskVo.getDingTaskId();
        DingTask dingTask = dingTaskMapper.findById(dingTaskId);
        LoginInfoVo loginInfo = userService.findCurrentLoginInfo();
        if (dingTask.getApplyStatus() == DingTaskApplyStatusEnum.DOING.getValue() && loginInfo != null) {
            Long dingTaskApplyId;
            DingTaskApply dingTaskApply = dingTaskApplyMapper.findByDingTaskIdAndApplyDate(dingTaskId, LocalDate.now());
            if (dingTaskApply == null) {
                // 今天还没有申请报名，则创建一个
                // 这里需要注意并发问题
                DingTaskApply taskApply = new DingTaskApply();
                taskApply.setDingTaskId(dingTaskId);
                taskApply.setDingTaskName(dingTask.getName());
                taskApply.setApplyDate(LocalDate.now());
                taskApply.setCompleted(0);
                dingTaskApplyMapper.create(taskApply);
                dingTaskApplyId = taskApply.getId();
            } else {
                dingTaskApplyId = dingTaskApply.getId();
            }

            DingTaskApplyStaff dingTaskApplyStaff = dingTaskApplyStaffMapper.findByDingTaskApplyIdAndStaffId(dingTaskApplyId, loginInfo.getId());
            if (dingTaskApplyStaff == null) {
                // 申请报名
                DingTaskApplyStaff applyStaff = new DingTaskApplyStaff();
                applyStaff.setDingTaskApplyId(dingTaskApplyId);
                applyStaff.setStaffId(loginInfo.getId());
                applyStaff.setRemark(applyDingTaskVo.getRemark());
                applyStaff.setCancelled(0);
                dingTaskApplyStaffMapper.create(applyStaff);
            } else if (dingTaskApplyStaff.getCancelled() == 1) {
                // 取消过报名，修改取消状态
                dingTaskApplyStaff.setCancelled(0);
                if (applyDingTaskVo.getRemark() != null && applyDingTaskVo.getRemark().length() > 0) {
                    dingTaskApplyStaff.setRemark(applyDingTaskVo.getRemark());
                }
                dingTaskApplyStaffMapper.update(dingTaskApplyStaff);
            } else {
                // 更新备注
                dingTaskApplyStaff.setRemark(applyDingTaskVo.getRemark());
                dingTaskApplyStaffMapper.update(dingTaskApplyStaff);
            }
        }
    }

    @Override
    public void cancelApplyDingTask(Long dingTaskId) {
        DingTaskApply dingTaskApply = dingTaskApplyMapper.findByDingTaskIdAndApplyDate(dingTaskId, LocalDate.now());
        LoginInfoVo loginInfo = userService.findCurrentLoginInfo();
        if (dingTaskApply != null && loginInfo != null) {
            DingTaskApplyStaff dingTaskApplyStaff = dingTaskApplyStaffMapper.findByDingTaskApplyIdAndStaffId(dingTaskApply.getId(), loginInfo.getId());
            if (dingTaskApplyStaff != null) {
                // 取消报名
                dingTaskApplyStaffMapper.cancel(dingTaskApplyStaff.getId());
            }
        }
    }

    @Override
    public List<DingTaskApplyStaff> findDingTaskApplyStaffByDingTaskId(Long dingTaskId) {
        DingTaskApply dingTaskApply = dingTaskApplyMapper.findByDingTaskIdAndApplyDate(dingTaskId, LocalDate.now());
        if (dingTaskApply != null) {
            return dingTaskApplyStaffMapper.findByDingTaskApplyId(dingTaskApply.getId());
        }
        return new ArrayList<>();
    }
}
