package com.auto.post.posting;

import java.util.List;

/**
 * FB 自动发帖服务。
 */
public interface PostService {

    /**
     * 查询发帖记录列表。
     *
     * @return 发帖记录列表
     */
    List<PostRecord> listRecords();

    /**
     * 基于素材库生成一条 FB 发帖计划。
     *
     * @param request 生成计划请求
     * @return 新生成的发帖记录
     */
    PostRecord generate(GeneratePostRequest request);

    /**
     * 审核通过待审核发帖记录。
     *
     * @param id 发帖记录 ID
     * @return 更新后的发帖记录
     */
    PostRecord approve(Long id);

    /**
     * 驳回待审核发帖记录。
     *
     * @param id 发帖记录 ID
     * @return 更新后的发帖记录
     */
    PostRecord reject(Long id);
}
