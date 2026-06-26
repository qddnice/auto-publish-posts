package com.auto.post.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * TT advertiser 资产实体，对应 ap_tt_advertiser 表，用于 TT 智能回复绑定广告主。
 */
@TableName("ap_tt_advertiser")
public class TtAdvertiserEntity {

    /**
     * 资产主键。
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联平台凭证主键。
     */
    private Long credentialId;

    /**
     * TT Business Center ID。
     */
    private String bcId;

    /**
     * TT 广告主 ID。
     */
    private String advertiserId;

    /**
     * TT 广告主展示名称。
     */
    private String advertiserName;

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

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public String getBcId() {
        return bcId;
    }

    public void setBcId(String bcId) {
        this.bcId = bcId;
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
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
