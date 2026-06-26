package com.auto.post.auth;

import com.auto.post.common.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录认证接口。
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthLoginResult result = authService.login(request.username(), request.password());
        return ApiResponse.ok(new LoginResponse(result.token(), result.username(), result.role(), result.displayName()));
    }

    /**
     * 登录请求参数。
     *
     * @param username 登录名
     * @param password 登录密码
     */
    public record LoginRequest(
            @NotBlank(message = "登录名不能为空") String username,
            @NotBlank(message = "密码不能为空") String password
    ) {
    }

    /**
     * 登录成功响应。
     *
     * @param token JWT 访问令牌
     * @param username 登录名
     * @param role 用户角色
     * @param displayName 用户展示名称
     */
    public record LoginResponse(String token, String username, String role, String displayName) {
    }
}
