package com.example.bodydiary.api.User.dto;

import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDto {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须为3-20个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    // @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,}$", message = "密码必须包含至少一个数字、一个字母和一个特殊字符")
    private String password;
}
