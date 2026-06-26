package com.auto.post.reply.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * 评论或私信消息记录实体，对应 ap_message_record 表。
 */
@TableName("ap_message_record")
public class MessageRecordEntity {
    /** 消息记录主键。 */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 平台编码。 */
    private String platform;
    /** 消息类型。 */
    private String messageType;
    /** 来源主页或账号 ID。 */
    private String sourceId;
    /** 来源主页或账号名称。 */
    private String sourceName;
    /** 平台消息 ID。 */
    private String platformMessageId;
    /** 原始内容。 */
    private String originalContent;
    /** 回复内容。 */
    private String replyContent;
    /** 回复状态。 */
    private String replyStatus;
    /** 回复来源。 */
    private String replySource;
    /** 是否需要复查。 */
    private Boolean needsReview;
    /** 平台消息创建时间。 */
    private OffsetDateTime messageCreatedAt;
    /** 回复时间。 */
    private OffsetDateTime repliedAt;
    /** 窗口过期时间。 */
    private OffsetDateTime windowExpiresAt;
    /** 创建时间。 */
    private OffsetDateTime createdAt;
    /** 更新时间。 */
    private OffsetDateTime updatedAt;
    /** 逻辑删除标记。 */
    private Boolean deleted;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
    public String getMessageType() { return messageType; }
    public void setMessageType(String messageType) { this.messageType = messageType; }
    public String getSourceId() { return sourceId; }
    public void setSourceId(String sourceId) { this.sourceId = sourceId; }
    public String getSourceName() { return sourceName; }
    public void setSourceName(String sourceName) { this.sourceName = sourceName; }
    public String getPlatformMessageId() { return platformMessageId; }
    public void setPlatformMessageId(String platformMessageId) { this.platformMessageId = platformMessageId; }
    public String getOriginalContent() { return originalContent; }
    public void setOriginalContent(String originalContent) { this.originalContent = originalContent; }
    public String getReplyContent() { return replyContent; }
    public void setReplyContent(String replyContent) { this.replyContent = replyContent; }
    public String getReplyStatus() { return replyStatus; }
    public void setReplyStatus(String replyStatus) { this.replyStatus = replyStatus; }
    public String getReplySource() { return replySource; }
    public void setReplySource(String replySource) { this.replySource = replySource; }
    public Boolean getNeedsReview() { return needsReview; }
    public void setNeedsReview(Boolean needsReview) { this.needsReview = needsReview; }
    public OffsetDateTime getMessageCreatedAt() { return messageCreatedAt; }
    public void setMessageCreatedAt(OffsetDateTime messageCreatedAt) { this.messageCreatedAt = messageCreatedAt; }
    public OffsetDateTime getRepliedAt() { return repliedAt; }
    public void setRepliedAt(OffsetDateTime repliedAt) { this.repliedAt = repliedAt; }
    public OffsetDateTime getWindowExpiresAt() { return windowExpiresAt; }
    public void setWindowExpiresAt(OffsetDateTime windowExpiresAt) { this.windowExpiresAt = windowExpiresAt; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}
