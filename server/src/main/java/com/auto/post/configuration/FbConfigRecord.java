package com.auto.post.configuration;

/**
 * FB 运营配置响应/保存对象，用于 FB 智能回复与自动发帖配置页面。
 *
 * @param id 配置主键
 * @param pageId FB 主页 ID
 * @param pageName FB 主页名称
 * @param enableReply 是否启用智能回复
 * @param enablePost 是否启用自动发帖
 * @param replyIntervalMin 单主页回复间隔分钟数
 * @param dailyReplyLimit 单主页每日回复上限
 * @param postReviewMode 发帖审核模式，MANUAL/AUTO
 * @param status 配置状态，ENABLED/PAUSED/DISABLED
 */
public record FbConfigRecord(Long id, String pageId, String pageName, boolean enableReply,
                             boolean enablePost, int replyIntervalMin, int dailyReplyLimit,
                             String postReviewMode, String status) {
}
