package com.auto.post.reply;

/**
 * 评论或私信回复记录响应对象，用于运营工作台和人工审核队列。
 *
 * @param id 消息记录主键
 * @param platform 平台编码 FB/TT
 * @param messageType 消息类型 COMMENT/DM
 * @param sourceId 来源主页或账号 ID
 * @param sourceName 来源主页或账号名称
 * @param platformMessageId 平台消息 ID
 * @param originalContent 原始评论或私信内容
 * @param replyContent 回复内容
 * @param replyStatus 回复状态
 * @param replySource 回复来源 AUTO/MANUAL
 * @param needsReview 是否需要复查
 */
public record MessageRecord(Long id, String platform, String messageType, String sourceId,
                            String sourceName, String platformMessageId, String originalContent,
                            String replyContent, String replyStatus, String replySource,
                            boolean needsReview) {
}
