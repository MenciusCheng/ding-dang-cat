package com.marvel.dingdangcat.mapper.user;

import com.marvel.dingdangcat.domain.user.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marvel on 2019/9/29.
 */
@Repository
public interface RoleMapper {

    void create(Role entity);

    void update(Role entity);

    void delete(Long id);

    Role findById(Long id);

    List<Role> findAll();

    List<Role> findByAccountId(Long accountId);
}
