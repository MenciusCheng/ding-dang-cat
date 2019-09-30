package com.marvel.dingdangcat.mapper.ding;

import com.marvel.dingdangcat.domain.ding.DingTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marvel on 2019/9/30.
 */
@Repository
public interface DingTaskMapper {

    void create(DingTask entity);

    void update(DingTask entity);

    void delete(Long id);

    DingTask findById(Long id);

    List<DingTask> findAll();
}
