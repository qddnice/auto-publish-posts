package com.auto.post.reply.mapper;

import com.auto.post.reply.entity.SensitiveWordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ap_sensitive_word 表访问接口。
 */
@Mapper
public interface SensitiveWordMapper extends BaseMapper<SensitiveWordEntity> {
}
