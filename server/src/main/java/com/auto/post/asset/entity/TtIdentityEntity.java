package com.auto.post.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * TT identity 资产实体，对应 ap_tt_identity 表，用于 TT 评论/私信回复配置选择账号身份。
 */
@TableName("ap_tt_identity")
public class TtIdentityEntity {

    /**
     * 资产主键。
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * TT Business Center ID。
     */
    private String bcId;

    /**
     * TT identity ID，用于平台回复身份。
     */
    private String identityId;

    /**
     * TT identity 展示名称。
     */
    private String identityName;

    /**
     * identity 类型，例如 BC_AUTH_TT。
     */
    private String identityType;

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

    public String getBcId() {
        return bcId;
    }

    public void setBcId(String bcId) {
        this.bcId = bcId;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
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
