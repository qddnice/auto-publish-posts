package com.auto.post.credential;

import com.auto.post.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 平台凭证管理接口，负责保存 FB/TT OAuth 或手动录入的 token 边界信息。
 */
@RestController
@RequestMapping("/api/credential")
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping("/list")
    public ApiResponse<List<CredentialDTO>> list(@RequestParam(required = false) String platform) {
        return ApiResponse.ok(credentialService.list(platform));
    }

    @PostMapping("/save")
    public ApiResponse<CredentialDTO> save(@RequestBody CredentialDTO request) {
        return ApiResponse.ok(credentialService.save(request));
    }
}
