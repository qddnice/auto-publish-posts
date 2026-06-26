package com.auto.post.posting;

import java.time.OffsetDateTime;

/**
 * 生成发帖计划请求。
 *
 * @param pageId FB 主页 ID
 * @param pageName FB 主页名称
 * @param libraryId 素材库 ID
 * @param reviewMode 审核模式 MANUAL/AUTO
 * @param scheduledAt 计划发布时间
 */
public record GeneratePostRequest(String pageId, String pageName, Long libraryId,
                                  String reviewMode, OffsetDateTime scheduledAt) {
}
