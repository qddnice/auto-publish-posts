package com.auto.post.configuration;

/**
 * TT 运营配置响应/保存对象，用于 TT 评论和私信智能回复配置页面。
 *
 * @param id 配置主键
 * @param credentialId 凭证业务 ID
 * @param bcId TT Business Center ID
 * @param bcName TT Business Center 名称
 * @param orgId TT 账号或组织 ID
 * @param orgName TT 账号或组织名称
 * @param linkedAdvertiserId 绑定广告主 ID
 * @param linkedAdvertiserName 绑定广告主名称
 * @param enableCommentReply 是否启用评论回复
 * @param enableDmReply 是否启用私信回复
 * @param replyIntervalMin 单账号回复间隔分钟数
 * @param dailyReplyLimit 单账号每日回复上限
 * @param status 配置状态，ENABLED/PAUSED/DISABLED
 */
public record TtConfigRecord(Long id, String credentialId, String bcId, String bcName,
                             String orgId, String orgName, String linkedAdvertiserId,
                             String linkedAdvertiserName, boolean enableCommentReply,
                             boolean enableDmReply, int replyIntervalMin, int dailyReplyLimit,
                             String status) {
}
