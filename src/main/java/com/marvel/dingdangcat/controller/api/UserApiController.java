package com.marvel.dingdangcat.controller.api;

import com.marvel.dingdangcat.domain.user.Permission;
import com.marvel.dingdangcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Marvel on 2019/9/27.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/permission/save")
    public Permission savePermission(Permission permission) {
        return userService.savePermission(permission);
    }

    @GetMapping("/permission/get/{id}")
    public Permission findPermissionById(@PathVariable Long id) {
        return userService.findPermissionById(id);
    }

    @GetMapping("/permission/all")
    public Page<Permission> findAllPermissions(Pageable pageable) {
        return userService.findAllPermissions(pageable);
    }
}
