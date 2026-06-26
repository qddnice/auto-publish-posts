package com.auto.post.reply.mapper;

import com.auto.post.reply.entity.MessageRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ap_message_record 表访问接口。
 */
@Mapper
public interface MessageRecordMapper extends BaseMapper<MessageRecordEntity> {
}
