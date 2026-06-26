package com.auto.post.reply.model;

/**
 * 置信度计算结果。
 *
 * @param level 置信度等级：HIGH/MEDIUM/LOW
 * @param finalScore 规则修正后的最终分值
 * @param shouldAutoReply 是否允许自动回复
 * @param needsReview 自动回复后是否需要人工复查
 */
public record ConfidenceResult(String level, double finalScore, boolean shouldAutoReply, boolean needsReview) {
}
