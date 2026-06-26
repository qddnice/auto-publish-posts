package com.auto.post.configuration.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * FB 运营配置实体，对应 ap_fb_config 表，用于控制 FB 智能回复和自动发帖。
 */
@TableName("ap_fb_config")
public class FbConfigEntity {
    /** 配置主键。 */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** FB 主页 ID。 */
    private String pageId;
    /** FB 主页名称。 */
    private String pageName;
    /** 是否启用智能回复。 */
    private Boolean enableReply;
    /** 是否启用自动发帖。 */
    private Boolean enablePost;
    /** 回复间隔分钟数。 */
    private Integer replyIntervalMin;
    /** 每日回复上限。 */
    private Integer dailyReplyLimit;
    /** 发帖审核模式。 */
    private String postReviewMode;
    /** 配置状态。 */
    private String status;
    /** 更新时间。 */
    private OffsetDateTime updatedAt;
    /** 逻辑删除标记。 */
    private Boolean deleted;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPageId() { return pageId; }
    public void setPageId(String pageId) { this.pageId = pageId; }
    public String getPageName() { return pageName; }
    public void setPageName(String pageName) { this.pageName = pageName; }
    public Boolean getEnableReply() { return enableReply; }
    public void setEnableReply(Boolean enableReply) { this.enableReply = enableReply; }
    public Boolean getEnablePost() { return enablePost; }
    public void setEnablePost(Boolean enablePost) { this.enablePost = enablePost; }
    public Integer getReplyIntervalMin() { return replyIntervalMin; }
    public void setReplyIntervalMin(Integer replyIntervalMin) { this.replyIntervalMin = replyIntervalMin; }
    public Integer getDailyReplyLimit() { return dailyReplyLimit; }
    public void setDailyReplyLimit(Integer dailyReplyLimit) { this.dailyReplyLimit = dailyReplyLimit; }
    public String getPostReviewMode() { return postReviewMode; }
    public void setPostReviewMode(String postReviewMode) { this.postReviewMode = postReviewMode; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}
