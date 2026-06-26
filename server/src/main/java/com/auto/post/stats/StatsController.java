package com.auto.post.stats;

import com.auto.post.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 运营数据统计接口。
 */
@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/summary")
    public ApiResponse<StatsSummary> summary() {
        return ApiResponse.ok(statsService.summary());
    }

    @GetMapping("/trend")
    public ApiResponse<List<Map<String, Object>>> trend() {
        return ApiResponse.ok(statsService.trend());
    }
}
