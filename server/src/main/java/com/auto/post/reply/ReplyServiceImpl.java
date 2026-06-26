package com.auto.post.reply;

import com.auto.post.common.BizException;
import com.auto.post.reply.entity.MessageRecordEntity;
import com.auto.post.reply.mapper.MessageRecordMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * 基于 ap_message_record 的智能回复运营服务实现。
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    private final MessageRecordMapper messageRecordMapper;
    private final SensitiveWordService sensitiveWordService;
    private final SensitiveWordDetector sensitiveWordDetector;

    public ReplyServiceImpl(MessageRecordMapper messageRecordMapper,
                            SensitiveWordService sensitiveWordService,
                            SensitiveWordDetector sensitiveWordDetector) {
        this.messageRecordMapper = messageRecordMapper;
        this.sensitiveWordService = sensitiveWordService;
        this.sensitiveWordDetector = sensitiveWordDetector;
    }

    @Override
    public List<MessageRecord> workbench(String platform) {
        return messageRecordMapper.selectList(Wrappers.<MessageRecordEntity>lambdaQuery()
                        .eq(MessageRecordEntity::getDeleted, false)
                        .eq(platform != null && !platform.isBlank(), MessageRecordEntity::getPlatform, platform)
                        .orderByDesc(MessageRecordEntity::getCreatedAt))
                .stream().map(this::toRecord).toList();
    }

    @Override
    public List<MessageRecord> reviewQueue() {
        return messageRecordMapper.selectList(Wrappers.<MessageRecordEntity>lambdaQuery()
                        .eq(MessageRecordEntity::getDeleted, false)
                        .eq(MessageRecordEntity::getReplyStatus, "MANUAL_REVIEW")
                        .orderByAsc(MessageRecordEntity::getWindowExpiresAt))
                .stream().map(this::toRecord).toList();
    }

    @Override
    public MessageRecord manualReply(Long id, String replyContent) {
        SensitiveWordDetector.Result check = sensitiveWordDetector.check("ALL", replyContent, sensitiveWordService.listRules());
        if (check.blocked()) {
            throw new BizException("回复内容命中违禁词：" + String.join(",", check.hitWords()));
        }
        MessageRecordEntity entity = messageRecordMapper.selectById(id);
        if (entity == null || Boolean.TRUE.equals(entity.getDeleted())) {
            throw new BizException("消息记录不存在");
        }
        entity.setReplyContent(replyContent);
        entity.setReplyStatus("REPLIED");
        entity.setReplySource("MANUAL");
        entity.setNeedsReview(false);
        entity.setRepliedAt(OffsetDateTime.now());
        entity.setUpdatedAt(OffsetDateTime.now());
        messageRecordMapper.updateById(entity);
        return toRecord(entity);
    }

    private MessageRecord toRecord(MessageRecordEntity entity) {
        return new MessageRecord(entity.getId(), entity.getPlatform(), entity.getMessageType(), entity.getSourceId(),
                entity.getSourceName(), entity.getPlatformMessageId(), entity.getOriginalContent(),
                entity.getReplyContent(), entity.getReplyStatus(), entity.getReplySource(),
                Boolean.TRUE.equals(entity.getNeedsReview()));
    }
}
