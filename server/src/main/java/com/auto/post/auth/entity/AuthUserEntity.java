package com.auto.post.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * 本地登录用户实体，对应 ap_user 表，用于独立项目的运营/管理员登录认证。
 */
@TableName("ap_user")
public class AuthUserEntity {

    /**
     * 用户主键。
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 登录用户名，系统内唯一。
     */
    private String username;

    /**
     * 密码哈希，支持 Spring Security DelegatingPasswordEncoder 格式。
     */
    private String passwordHash;

    /**
     * 用户展示名称，用于前端页面和操作审计展示。
     */
    private String displayName;

    /**
     * 用户角色，当前支持 ADMIN 和 OPERATOR。
     */
    private String role;

    /**
     * 创建时间。
     */
    private OffsetDateTime createdAt;

    /**
     * 更新时间。
     */
    private OffsetDateTime updatedAt;

    /**
     * 创建人。
     */
    private String createdBy;

    /**
     * 更新人。
     */
    private String updatedBy;

    /**
     * 逻辑删除标记，true 表示不可登录。
     */
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
