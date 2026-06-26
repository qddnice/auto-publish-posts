package com.auto.post.auth;

import com.auto.post.auth.entity.AuthUserEntity;
import com.auto.post.auth.mapper.UserMapper;
import com.auto.post.common.BizException;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 基于 ap_user 表的本地用户认证实现。
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserMapper userMapper, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthLoginResult login(String username, String password) {
        AuthUserEntity user = userMapper.selectOne(Wrappers.<AuthUserEntity>lambdaQuery()
                .eq(AuthUserEntity::getUsername, username)
                .eq(AuthUserEntity::getDeleted, false)
                .last("limit 1"));
        if (user == null || !passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new BizException(401, "用户名或密码错误");
        }
        String token = jwtService.issue(user.getUsername(), user.getRole());
        return new AuthLoginResult(token, user.getUsername(), user.getRole(), user.getDisplayName());
    }
}
