package com.example.bodydiary.api.User.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String name;
    private String email;
    private String password;
    private String phone;
}
