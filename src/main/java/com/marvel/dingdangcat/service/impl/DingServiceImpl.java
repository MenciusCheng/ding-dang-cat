package com.marvel.dingdangcat.service.impl;

import com.marvel.dingdangcat.constant.DingTaskApplyStatusEnum;
import com.marvel.dingdangcat.constant.DingTaskNoticeTypeEnum;
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
import com.marvel.dingdangcat.service.DingMessageService;
import com.marvel.dingdangcat.service.DingService;
import com.marvel.dingdangcat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marvel on 2019/9/30.
 */
@Service
public class DingServiceImpl implements DingService {

    private final Logger logger = LoggerFactory.getLogger(DingServiceImpl.class);

    private final DingTaskMapper dingTaskMapper;
    private final DingTaskApplyMapper dingTaskApplyMapper;
    private final DingTaskApplyStaffMapper dingTaskApplyStaffMapper;
    private final UserService userService;
    private final DingMessageService dingMessageService;

    @Autowired
    public DingServiceImpl(
            DingTaskMapper dingTaskMapper,
            DingTaskApplyMapper dingTaskApplyMapper,
            DingTaskApplyStaffMapper dingTaskApplyStaffMapper,
            UserService userService,
            DingMessageService dingMessageService) {
        this.dingTaskMapper = dingTaskMapper;
        this.dingTaskApplyMapper = dingTaskApplyMapper;
        this.dingTaskApplyStaffMapper = dingTaskApplyStaffMapper;
        this.userService = userService;
        this.dingMessageService = dingMessageService;
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
                dingTaskApplyStaff.setRemark(applyDingTaskVo.getRemark());
                dingTaskApplyStaffMapper.update(dingTaskApplyStaff);
            } else {
                // 更新备注
                dingTaskApplyStaff.setRemark(applyDingTaskVo.getRemark());
                dingTaskApplyStaffMapper.update(dingTaskApplyStaff);
            }

            // 有报名时，发送提醒
            if (containNoticeType(dingTask.getNoticeType(), DingTaskNoticeTypeEnum.APPLY.getValue())) {
                dingMessageService.sendDingTalkWithApply(dingTaskId);
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
                DingTask dingTask = dingTaskMapper.findById(dingTaskId);
                // 有报名时，发送提醒
                if (containNoticeType(dingTask.getNoticeType(), DingTaskNoticeTypeEnum.APPLY.getValue())) {
                    dingMessageService.sendDingTalkWithApply(dingTaskId);
                }
            }
        }
    }

    @Override
    public List<DingTaskApply> findDingTaskApplyByDingTaskId(Long dingTaskId) {
        return dingTaskApplyMapper.findByDingTaskId(dingTaskId);
    }

    @Override
    public List<DingTaskApplyStaff> findTodayDingTaskApplyStaffByDingTaskId(Long dingTaskId) {
        return findDingTaskApplyStaffByDingTaskIdAndApplyDate(dingTaskId, LocalDate.now());
    }

    @Override
    public List<DingTaskApplyStaff> findDingTaskApplyStaffByDingTaskIdAndApplyDate(Long dingTaskId, LocalDate applyDate) {
        DingTaskApply dingTaskApply = dingTaskApplyMapper.findByDingTaskIdAndApplyDate(dingTaskId, applyDate);
        if (dingTaskApply != null) {
            return dingTaskApplyStaffMapper.findByDingTaskApplyId(dingTaskApply.getId());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean containNoticeType(String noticeType, Integer value) {
        List<Integer> noticeTypeList = getNoticeTypeList(noticeType);
        return noticeTypeList.contains(value);
    }

    /**
     * 提醒类型转为列表
     */
    private List<Integer> getNoticeTypeList(String noticeType) {
        if (noticeType != null && noticeType.length() > 0) {
            String[] split = noticeType.split(",");
            return Arrays.stream(split).map(Integer::valueOf).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
