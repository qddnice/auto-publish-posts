package com.auto.post.reply;

import com.auto.post.common.ApiResponse;
import com.auto.post.reply.model.SensitiveWordRule;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 违禁词管理接口。
 */
@RestController
@RequestMapping("/api/sensitive-word")
public class SensitiveWordController {

    private final SensitiveWordService sensitiveWordService;

    public SensitiveWordController(SensitiveWordService sensitiveWordService) {
        this.sensitiveWordService = sensitiveWordService;
    }

    @GetMapping("/list")
    public ApiResponse<List<SensitiveWordRule>> list() {
        return ApiResponse.ok(sensitiveWordService.listRules());
    }

    @PostMapping("/save")
    public ApiResponse<SensitiveWordRule> save(@RequestBody SensitiveWordRule rule) {
        return ApiResponse.ok(sensitiveWordService.saveRule(rule));
    }

    @PostMapping("/check")
    public ApiResponse<SensitiveWordDetector.Result> check(@RequestBody CheckRequest request) {
        return ApiResponse.ok(sensitiveWordService.check(request.platform(), request.content()));
    }

    /**
     * 违禁词检测请求。
     *
     * @param platform 平台编码 FB/TT
     * @param content 待检测内容
     */
    public record CheckRequest(String platform, String content) {
    }
}
