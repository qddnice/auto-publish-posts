package com.auto.post.configuration.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * TT 运营配置实体，对应 ap_tt_config 表，用于控制 TT 评论和私信智能回复。
 */
@TableName("ap_tt_config")
public class TtConfigEntity {
    /** 配置主键。 */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 凭证业务 ID。 */
    private String credentialId;
    /** TT Business Center ID。 */
    private String bcId;
    /** TT Business Center 名称。 */
    private String bcName;
    /** TT 账号或组织 ID。 */
    private String orgId;
    /** TT 账号或组织名称。 */
    private String orgName;
    /** 绑定广告主 ID。 */
    private String linkedAdvertiserId;
    /** 绑定广告主名称。 */
    private String linkedAdvertiserName;
    /** 是否启用评论回复。 */
    private Boolean enableCommentReply;
    /** 是否启用私信回复。 */
    private Boolean enableDmReply;
    /** 回复间隔分钟数。 */
    private Integer replyIntervalMin;
    /** 每日回复上限。 */
    private Integer dailyReplyLimit;
    /** 配置状态。 */
    private String status;
    /** 更新时间。 */
    private OffsetDateTime updatedAt;
    /** 逻辑删除标记。 */
    private Boolean deleted;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCredentialId() { return credentialId; }
    public void setCredentialId(String credentialId) { this.credentialId = credentialId; }
    public String getBcId() { return bcId; }
    public void setBcId(String bcId) { this.bcId = bcId; }
    public String getBcName() { return bcName; }
    public void setBcName(String bcName) { this.bcName = bcName; }
    public String getOrgId() { return orgId; }
    public void setOrgId(String orgId) { this.orgId = orgId; }
    public String getOrgName() { return orgName; }
    public void setOrgName(String orgName) { this.orgName = orgName; }
    public String getLinkedAdvertiserId() { return linkedAdvertiserId; }
    public void setLinkedAdvertiserId(String linkedAdvertiserId) { this.linkedAdvertiserId = linkedAdvertiserId; }
    public String getLinkedAdvertiserName() { return linkedAdvertiserName; }
    public void setLinkedAdvertiserName(String linkedAdvertiserName) { this.linkedAdvertiserName = linkedAdvertiserName; }
    public Boolean getEnableCommentReply() { return enableCommentReply; }
    public void setEnableCommentReply(Boolean enableCommentReply) { this.enableCommentReply = enableCommentReply; }
    public Boolean getEnableDmReply() { return enableDmReply; }
    public void setEnableDmReply(Boolean enableDmReply) { this.enableDmReply = enableDmReply; }
    public Integer getReplyIntervalMin() { return replyIntervalMin; }
    public void setReplyIntervalMin(Integer replyIntervalMin) { this.replyIntervalMin = replyIntervalMin; }
    public Integer getDailyReplyLimit() { return dailyReplyLimit; }
    public void setDailyReplyLimit(Integer dailyReplyLimit) { this.dailyReplyLimit = dailyReplyLimit; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}
