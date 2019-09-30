package com.marvel.dingdangcat.mapper.ding;

import com.marvel.dingdangcat.domain.ding.DingTaskApply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Created by Marvel on 2019/9/30.
 */
@Repository
public interface DingTaskApplyMapper {

    void create(DingTaskApply entity);

    void update(DingTaskApply entity);

    void complete(Long id);

    DingTaskApply findById(Long id);

    DingTaskApply findByDingTaskIdAndApplyDate(@Param("dingTaskId") Long dingTaskId, @Param("applyDate") LocalDate applyDate);
}
