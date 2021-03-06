package com.marvel.dingdangcat.mapper.ding;

import com.marvel.dingdangcat.domain.ding.DingTaskApplyStaff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marvel on 2019/9/30.
 */
@Repository
public interface DingTaskApplyStaffMapper {

    void create(DingTaskApplyStaff entity);

    void update(DingTaskApplyStaff entity);

    void cancel(Long id);

    void uncancel(Long id);

    DingTaskApplyStaff findById(Long id);

    List<DingTaskApplyStaff> findByDingTaskApplyId(Long dingTaskApplyId);

    DingTaskApplyStaff findByDingTaskApplyIdAndStaffId(@Param("dingTaskApplyId") Long dingTaskApplyId, @Param("staffId") Long staffId);
}
