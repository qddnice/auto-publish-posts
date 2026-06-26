package com.auto.post.configuration;

import java.util.List;
import java.util.Map;

/**
 * 运营配置服务。
 */
public interface OperationConfigService {

    /**
     * 查询 FB 主页运营配置列表。
     *
     * @return FB 配置列表
     */
    List<FbConfigRecord> listFbConfigs();

    /**
     * 保存 FB 主页运营配置，新增或按 ID 更新。
     *
     * @param request FB 配置保存参数
     * @return 保存后的 FB 配置
     */
    FbConfigRecord saveFbConfig(FbConfigRecord request);

    /**
     * 查询 TT 账号运营配置列表。
     *
     * @return TT 配置列表
     */
    List<TtConfigRecord> listTtConfigs();

    /**
     * 保存 TT 账号运营配置，新增或按 ID 更新。
     *
     * @param request TT 配置保存参数
     * @return 保存后的 TT 配置
     */
    TtConfigRecord saveTtConfig(TtConfigRecord request);

    /**
     * 统计灰度和启停状态概览。
     *
     * @return total/enabled/paused 三类计数
     */
    Map<String, Long> graySummary();

    /**
     * 执行紧急停止，将可运行配置暂停并取消待发布任务。
     *
     * @return 受影响配置数和取消任务数
     */
    Map<String, Integer> emergencyStop();
}
