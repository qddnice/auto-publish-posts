package com.auto.post.configuration;

import com.auto.post.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 运营配置接口，覆盖 FB/TT 参与配置、灰度概览和紧急停止。
 */
@RestController
@RequestMapping("/api/config")
public class OperationConfigController {

    private final OperationConfigService operationConfigService;

    public OperationConfigController(OperationConfigService operationConfigService) {
        this.operationConfigService = operationConfigService;
    }

    @GetMapping("/fb/list")
    public ApiResponse<List<FbConfigRecord>> fbList() {
        return ApiResponse.ok(operationConfigService.listFbConfigs());
    }

    @PostMapping("/fb/save")
    public ApiResponse<FbConfigRecord> saveFb(@RequestBody FbConfigRecord request) {
        return ApiResponse.ok(operationConfigService.saveFbConfig(request));
    }

    @GetMapping("/tt/list")
    public ApiResponse<List<TtConfigRecord>> ttList() {
        return ApiResponse.ok(operationConfigService.listTtConfigs());
    }

    @PostMapping("/tt/save")
    public ApiResponse<TtConfigRecord> saveTt(@RequestBody TtConfigRecord request) {
        return ApiResponse.ok(operationConfigService.saveTtConfig(request));
    }

    @GetMapping("/gray/summary")
    public ApiResponse<Map<String, Long>> graySummary() {
        return ApiResponse.ok(operationConfigService.graySummary());
    }

    @PostMapping("/emergency-stop")
    public ApiResponse<Map<String, Integer>> emergencyStop() {
        return ApiResponse.ok(operationConfigService.emergencyStop());
    }
}
