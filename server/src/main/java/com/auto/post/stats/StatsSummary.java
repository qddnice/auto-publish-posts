package com.auto.post.stats;

/**
 * 运营看板汇总数据。
 *
 * @param todayReplyCount 今日已回复数量
 * @param manualPendingCount 待人工审核数量
 * @param todayPostCount 今日已发布发帖数量
 * @param sensitiveHitCount 当前启用违禁词数量
 */
public record StatsSummary(long todayReplyCount, long manualPendingCount, long todayPostCount, long sensitiveHitCount) {
}
