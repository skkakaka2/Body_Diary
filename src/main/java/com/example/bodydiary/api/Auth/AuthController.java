package com.example.bodydiary.api.Auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bodydiary.common.Response;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("login")
    public Response login(@RequestBody LoginRequestDto loginDto) {
        return Response.success(null);
    }
}
