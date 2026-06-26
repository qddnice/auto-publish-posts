package com.auto.post.reply;

import com.auto.post.common.ApiResponse;
import com.auto.post.reply.model.ConfidenceResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 智能回复运营工作台与人工审核接口。
 */
@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyService replyService;
    private final ConfidenceCalculator confidenceCalculator;

    public ReplyController(ReplyService replyService,
                           ConfidenceCalculator confidenceCalculator) {
        this.replyService = replyService;
        this.confidenceCalculator = confidenceCalculator;
    }

    @GetMapping("/workbench")
    public ApiResponse<List<MessageRecord>> workbench(@RequestParam(required = false) String platform) {
        return ApiResponse.ok(replyService.workbench(platform));
    }

    @GetMapping("/review-queue")
    public ApiResponse<List<MessageRecord>> reviewQueue() {
        return ApiResponse.ok(replyService.reviewQueue());
    }

    @PostMapping("/confidence/evaluate")
    public ApiResponse<ConfidenceResult> evaluateConfidence(@RequestBody ProcessRequest request) {
        return ApiResponse.ok(confidenceCalculator.calculate(request.aiScore(), request.originalContent(),
                request.symbolOnly(), request.hasProductInfo()));
    }

    @PostMapping("/manual-reply")
    public ApiResponse<MessageRecord> manualReply(@RequestBody ManualReplyRequest request) {
        return ApiResponse.ok(replyService.manualReply(request.id(), request.replyContent()));
    }

    /**
     * 置信度评估请求，用于根据工作流分值和内容特征计算自动回复分层。
     *
     * @param originalContent 原始评论或私信内容
     * @param aiScore AI 原始分值
     * @param symbolOnly 是否纯符号或表情
     * @param hasProductInfo 是否有关联商品信息
     */
    public record ProcessRequest(String originalContent, double aiScore, boolean symbolOnly, boolean hasProductInfo) {
    }

    /**
     * 人工回复请求。
     *
     * @param id 消息记录 ID
     * @param replyContent 人工回复内容
     */
    public record ManualReplyRequest(Long id, String replyContent) {
    }
}
