package com.auto.post.reply;

import com.auto.post.reply.model.SensitiveWordRule;

import java.util.List;

/**
 * 违禁词管理服务。
 */
public interface SensitiveWordService {

    /**
     * 查询违禁词规则列表。
     *
     * @return 违禁词规则列表
     */
    List<SensitiveWordRule> listRules();

    /**
     * 保存违禁词规则，按 word 和 scope 新增或更新。
     *
     * @param rule 违禁词规则
     * @return 保存后的违禁词规则
     */
    SensitiveWordRule saveRule(SensitiveWordRule rule);

    /**
     * 使用数据库中的启用规则检测文本内容。
     *
     * @param platform 平台编码
     * @param content 待检测内容
     * @return 检测结果
     */
    SensitiveWordDetector.Result check(String platform, String content);
}
