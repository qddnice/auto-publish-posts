package com.auto.post.asset;

/**
 * 平台资产下拉选项，用于配置页面选择 FB Page、TT identity 和 TT advertiser。
 *
 * @param id 平台资产业务 ID，例如 page_id、identity_id、advertiser_id
 * @param name 平台资产展示名称
 */
public record AssetOption(String id, String name) {
}
