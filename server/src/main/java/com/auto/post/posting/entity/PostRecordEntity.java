package com.auto.post.posting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * FB 发帖记录实体，对应 ap_post_record 表。
 */
@TableName("ap_post_record")
public class PostRecordEntity {
    /** 发帖记录主键。 */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** FB 主页 ID。 */
    private String pageId;
    /** FB 主页名称。 */
    private String pageName;
    /** 素材 ID。 */
    private Long materialId;
    /** 素材库 ID。 */
    private Long libraryId;
    /** 发帖文案。 */
    private String postContent;
    /** 发帖图片 URL。 */
    private String postImageUrl;
    /** FB 平台发帖 ID。 */
    private String fbPostId;
    /** 发帖状态。 */
    private String status;
    /** 审核模式。 */
    private String reviewMode;
    /** 审核人 ID。 */
    private Long reviewedBy;
    /** 审核时间。 */
    private OffsetDateTime reviewedAt;
    /** 计划发布时间。 */
    private OffsetDateTime scheduledAt;
    /** 实际发布时间。 */
    private OffsetDateTime publishedAt;
    /** 失败原因。 */
    private String failReason;
    /** 重试次数。 */
    private Integer retryCount;
    /** 乐观锁版本号。 */
    private Integer version;
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
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long materialId) { this.materialId = materialId; }
    public Long getLibraryId() { return libraryId; }
    public void setLibraryId(Long libraryId) { this.libraryId = libraryId; }
    public String getPostContent() { return postContent; }
    public void setPostContent(String postContent) { this.postContent = postContent; }
    public String getPostImageUrl() { return postImageUrl; }
    public void setPostImageUrl(String postImageUrl) { this.postImageUrl = postImageUrl; }
    public String getFbPostId() { return fbPostId; }
    public void setFbPostId(String fbPostId) { this.fbPostId = fbPostId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReviewMode() { return reviewMode; }
    public void setReviewMode(String reviewMode) { this.reviewMode = reviewMode; }
    public Long getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(Long reviewedBy) { this.reviewedBy = reviewedBy; }
    public OffsetDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(OffsetDateTime reviewedAt) { this.reviewedAt = reviewedAt; }
    public OffsetDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(OffsetDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
    public OffsetDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(OffsetDateTime publishedAt) { this.publishedAt = publishedAt; }
    public String getFailReason() { return failReason; }
    public void setFailReason(String failReason) { this.failReason = failReason; }
    public Integer getRetryCount() { return retryCount; }
    public void setRetryCount(Integer retryCount) { this.retryCount = retryCount; }
    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}
