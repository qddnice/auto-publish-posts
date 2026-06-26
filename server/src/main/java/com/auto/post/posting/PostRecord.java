package com.auto.post.posting;

import java.time.OffsetDateTime;

/**
 * FB 发帖记录响应对象，用于发帖记录列表和审核操作结果。
 *
 * @param id 发帖记录主键
 * @param pageId FB 主页 ID
 * @param pageName FB 主页名称
 * @param materialId 素材 ID
 * @param postContent 发帖文案
 * @param postImageUrl 发帖图片 URL
 * @param status 发帖状态
 * @param reviewMode 审核模式
 * @param scheduledAt 计划发布时间
 * @param publishedAt 实际发布时间
 * @param failReason 失败原因
 */
public record PostRecord(Long id, String pageId, String pageName, Long materialId,
                         String postContent, String postImageUrl, String status,
                         String reviewMode, OffsetDateTime scheduledAt,
                         OffsetDateTime publishedAt, String failReason) {
}
