package com.auto.post.reply.model;

/**
 * 违禁词检测规则。
 *
 * @param word 违禁词内容
 * @param matchType 匹配方式：EXACT 精确匹配，FUZZY 模糊匹配
 * @param scope 适用范围：GLOBAL/FB/TT
 * @param active 是否启用
 */
public record SensitiveWordRule(String word, String matchType, String scope, boolean active) {
}
