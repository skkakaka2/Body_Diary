package com.example.bodydiary.api.User;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.bodydiary.api.User.dto.UserCreateDto;
import com.example.bodydiary.api.User.entity.UserEntity;
import com.example.bodydiary.api.User.mapper.UserMapper;

import jakarta.annotation.Resource;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * MyBatis-Plus User Service
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 根据ID查询用户
     */
    public UserEntity getUserById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * 根据用户名查询用户
     */
    public UserEntity getUserByUsername(String username) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getUsername, username);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 根据邮箱查询用户
     */
    public UserEntity getUserByEmail(String email) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getEmail, email);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 创建用户
     */
    public UserEntity createUser(UserCreateDto user) {
        String password = passwordEncoder.encode(user.getPassword());
        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .password(password)
                .build();
        userMapper.insert(userEntity);
        return userEntity;
    }

    /**
     * 更新用户
     */
    public UserEntity updateUser(UserEntity user) {
        int rows = userMapper.updateById(user);
        return rows > 0 ? user : null;
    }

    /**
     * 删除用户
     */
    public boolean deleteUser(Long id) {
        return userMapper.deleteById(id) > 0;
    }
}
