package com.auto.post.material;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * 素材响应/保存对象，用于素材管理和自动发帖选材。
 *
 * @param id 素材主键
 * @param libraryId 所属素材库 ID
 * @param type 素材类型，IMAGE/TEXT/IMAGE_TEXT
 * @param imageUrl 图片公网 URL
 * @param textContent 发帖文案
 * @param tags 素材标签
 * @param status 素材状态
 * @param usedCount 使用次数
 * @param lastUsedAt 最近使用时间
 */
public record MaterialRecord(Long id, Long libraryId, String type, String imageUrl,
                             String textContent, List<String> tags, String status,
                             int usedCount, OffsetDateTime lastUsedAt) {
}
