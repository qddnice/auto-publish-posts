package com.auto.post.stats;

import java.util.List;
import java.util.Map;

/**
 * 运营统计服务。
 */
public interface StatsService {

    /**
     * 查询运营看板汇总数据。
     *
     * @return 运营看板汇总
     */
    StatsSummary summary();

    /**
     * 查询按日期和平台聚合的回复趋势。
     *
     * @return 趋势数据列表
     */
    List<Map<String, Object>> trend();
}
