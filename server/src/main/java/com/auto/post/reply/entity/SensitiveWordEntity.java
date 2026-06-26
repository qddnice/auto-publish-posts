package com.auto.post.reply.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * 违禁词实体，对应 ap_sensitive_word 表。
 */
@TableName("ap_sensitive_word")
public class SensitiveWordEntity {
    /** 违禁词主键。 */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 违禁词内容。 */
    private String word;
    /** 匹配方式。 */
    private String matchType;
    /** 生效范围。 */
    private String scope;
    /** 状态，ACTIVE 表示启用。 */
    private String status;
    /** 更新时间。 */
    private OffsetDateTime updatedAt;
    /** 逻辑删除标记。 */
    private Boolean deleted;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }
    public String getMatchType() { return matchType; }
    public void setMatchType(String matchType) { this.matchType = matchType; }
    public String getScope() { return scope; }
    public void setScope(String scope) { this.scope = scope; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}
