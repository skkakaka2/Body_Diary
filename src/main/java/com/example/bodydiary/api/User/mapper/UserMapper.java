package com.example.bodydiary.api.User.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bodydiary.api.User.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
