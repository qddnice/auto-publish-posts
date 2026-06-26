package com.auto.post.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * FB 主页资产实体，对应 ap_fb_page 表，用于智能回复配置和自动发帖配置选择主页。
 */
@TableName("ap_fb_page")
public class FbPageEntity {

    /**
     * 资产主键。
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * FB 平台主页 ID。
     */
    private String pageId;

    /**
     * FB 主页展示名称。
     */
    private String pageName;

    /**
     * 关联平台凭证主键。
     */
    private Long credentialId;

    /**
     * 加密后的 Page Access Token。
     */
    private String pageAccessTokenEncrypted;

    /**
     * 资产状态，ACTIVE 表示可用于配置。
     */
    private String status;

    /**
     * 所属团队 ID，用于后续团队隔离。
     */
    private Long teamId;

    /**
     * 更新时间，用于下拉选项排序。
     */
    private OffsetDateTime updatedAt;

    /**
     * 逻辑删除标记。
     */
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public String getPageAccessTokenEncrypted() {
        return pageAccessTokenEncrypted;
    }

    public void setPageAccessTokenEncrypted(String pageAccessTokenEncrypted) {
        this.pageAccessTokenEncrypted = pageAccessTokenEncrypted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
