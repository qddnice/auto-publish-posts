package com.auto.post.asset;

import com.auto.post.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 轻量平台资产查询接口，提供配置页级联选择数据。
 */
@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/fb-pages")
    public ApiResponse<List<AssetOption>> fbPages() {
        return ApiResponse.ok(assetService.listFbPages());
    }

    @GetMapping("/tt-identities")
    public ApiResponse<List<AssetOption>> ttIdentities() {
        return ApiResponse.ok(assetService.listTtIdentities());
    }

    @GetMapping("/tt-advertisers")
    public ApiResponse<List<AssetOption>> ttAdvertisers() {
        return ApiResponse.ok(assetService.listTtAdvertisers());
    }
}
