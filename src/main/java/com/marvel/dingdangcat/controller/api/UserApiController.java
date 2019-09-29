package com.marvel.dingdangcat.controller.api;

import com.marvel.dingdangcat.domain.user.Permission;
import com.marvel.dingdangcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/permission/create")
    public Long createPermission(Permission permission) {
        userService.createPermission(permission);
        return permission.getId();
    }

    @PostMapping("/permission/update")
    public String updatePermission(Permission permission) {
        userService.updatePermission(permission);
        return "success";
    }

    @PostMapping("/permission/delete")
    public String deletePermission(Long id) {
        userService.deletePermission(id);
        return "success";
    }

    @GetMapping("/permission/get/{id}")
    public Permission findPermissionById(@PathVariable Long id) {
        return userService.findPermissionById(id);
    }

    @GetMapping("/permission/all")
    public List<Permission> findAllPermissions() {
        return userService.findAllPermissions();
    }

    @GetMapping("/permission/roleId")
    public List<Permission> findPermissionsByRoleId(Long roleId) {
        return userService.findPermissionsByRoleId(roleId);
    }
}
