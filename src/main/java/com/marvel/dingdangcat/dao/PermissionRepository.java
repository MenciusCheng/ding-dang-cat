package com.marvel.dingdangcat.dao;

import com.marvel.dingdangcat.domain.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marvel on 2019/9/27.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
