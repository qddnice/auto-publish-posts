package com.auto.post.reply;

import java.util.List;

/**
 * 智能回复运营服务。
 */
public interface ReplyService {

    /**
     * 查询回复工作台消息记录。
     *
     * @param platform 平台编码，可为空
     * @return 消息记录列表
     */
    List<MessageRecord> workbench(String platform);

    /**
     * 查询待人工审核的消息记录。
     *
     * @return 人工审核队列
     */
    List<MessageRecord> reviewQueue();

    /**
     * 提交人工回复，检查违禁词后更新消息回复状态。
     *
     * @param id 消息记录 ID
     * @param replyContent 人工回复内容
     * @return 更新后的消息记录
     */
    MessageRecord manualReply(Long id, String replyContent);
}
