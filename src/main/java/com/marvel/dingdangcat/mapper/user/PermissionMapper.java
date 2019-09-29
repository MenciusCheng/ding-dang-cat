package com.marvel.dingdangcat.mapper.user;

import com.marvel.dingdangcat.domain.user.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marvel on 2019/9/29.
 */
@Repository
public interface PermissionMapper {

    void create(Permission entity);

    void update(Permission entity);

    void delete(Long id);

    Permission findById(Long id);

    List<Permission> findAll();

    List<Permission> findByRoleId(Long roleId);
}
