package com.auto.post.asset;

import java.util.List;

/**
 * 平台资产查询服务。
 */
public interface AssetService {

    /**
     * 查询可用于 FB 智能回复和自动发帖配置的有效主页。
     *
     * @return FB Page 下拉选项列表
     */
    List<AssetOption> listFbPages();

    /**
     * 查询可用于 TT 智能回复配置的有效 identity。
     *
     * @return TT identity 下拉选项列表
     */
    List<AssetOption> listTtIdentities();

    /**
     * 查询可用于 TT 智能回复配置的有效 advertiser。
     *
     * @return TT advertiser 下拉选项列表
     */
    List<AssetOption> listTtAdvertisers();
}
