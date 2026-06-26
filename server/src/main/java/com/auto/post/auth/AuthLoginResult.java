package com.auto.post.auth;

/**
 * 登录认证结果，承载数据库用户校验通过后返回给 Controller 的令牌和用户身份信息。
 *
 * @param token JWT 访问令牌
 * @param username 登录用户名
 * @param role 用户角色，用于前端权限控制
 * @param displayName 用户展示名称，用于页面展示操作人
 */
public record AuthLoginResult(String token, String username, String role, String displayName) {
}
