package com.auto.post.auth;

/**
 * 本地用户认证服务。
 */
public interface AuthService {

    /**
     * 使用本地用户表校验账号密码，校验通过后签发 JWT。
     *
     * @param username 登录用户名
     * @param password 登录明文密码
     * @return 登录认证结果，包含 JWT、用户名、角色和展示名
     */
    AuthLoginResult login(String username, String password);
}
