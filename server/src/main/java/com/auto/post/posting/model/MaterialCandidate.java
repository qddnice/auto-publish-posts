package com.auto.post.posting.model;

import java.time.OffsetDateTime;

/**
 * 发帖素材候选项。
 *
 * @param id 素材 ID
 * @param active 是否启用并可参与选择
 * @param lastUsedAt 最后一次使用时间，空表示从未使用
 * @param usedCount 累计使用次数
 */
public record MaterialCandidate(Long id, boolean active, OffsetDateTime lastUsedAt, int usedCount) {
}
