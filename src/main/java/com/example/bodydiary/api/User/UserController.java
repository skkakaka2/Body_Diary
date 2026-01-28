package com.example.bodydiary.api.User;

import com.example.bodydiary.api.User.dto.UserCreateDto;
import com.example.bodydiary.api.User.entity.UserEntity;
import com.example.bodydiary.common.Response;
import com.example.bodydiary.common.ResponseCode;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 根据ID查询用户
     */
    @GetMapping("/{id}")
    public Response getUserById(@PathVariable Long id) {
        UserEntity user = userService.getUserById(id);
        if (user == null) {
            return Response.error(ResponseCode.NOT_FOUND, "User not found");
        }
        return Response.success(user);
    }

    /**
     * 根据用户名查询用户
     */
    @GetMapping("/username")
    public Response getUserByUsername(@RequestParam String username) {
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            return Response.error(ResponseCode.NOT_FOUND, "User not found");
        }
        return Response.success(user);
    }

    /**
     * 创建用户
     */
    @PostMapping("")
    public Response createUser(@RequestBody UserCreateDto user) {
        UserEntity created = userService.createUser(user);
        return Response.success(created);
    }

    /**
     * 更新用户
     */
    @PutMapping("/{id}")
    public Response updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        user.setId(id);
        UserEntity updated = userService.updateUser(user);
        if (updated == null) {
            return Response.error(ResponseCode.NOT_FOUND, "User not found");
        }
        return Response.success(updated);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable Long id) {
        boolean success = userService.deleteUser(id);
        if (!success) {
            return Response.error(ResponseCode.NOT_FOUND, "User not found");
        }
        return Response.success(null);
    }
}
