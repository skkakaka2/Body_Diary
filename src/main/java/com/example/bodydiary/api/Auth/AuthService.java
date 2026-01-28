package com.example.bodydiary.api.Auth;

import java.util.Locale.LanguageRange;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.bodydiary.api.User.entity.UserEntity;
import com.example.bodydiary.api.User.mapper.UserMapper;

import jakarta.annotation.Resource;

@Service
public class AuthService {

    @Resource
    UserMapper userMapper;

    @Resource
    PasswordEncoder passwordEncoder;

    public String login(LoginRequestDto params) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getUsername, params.getUsername());
        UserEntity user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!passwordEncoder.matches(params.getPassword(), user.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        return JwtUtils.generateToken(user.getId());

    }
}
