package com.auto.post.auth.mapper;

import com.auto.post.auth.entity.AuthUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ap_user 表访问接口，用于本地用户登录认证。
 */
@Mapper
public interface UserMapper extends BaseMapper<AuthUserEntity> {
}
