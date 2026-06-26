package com.auto.post.posting;

import com.auto.post.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * FB 自动发帖接口，覆盖计划生成、审核和记录查看。
 */
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/records")
    public ApiResponse<List<PostRecord>> records() {
        return ApiResponse.ok(postService.listRecords());
    }

    @PostMapping("/generate")
    public ApiResponse<PostRecord> generate(@RequestBody GeneratePostRequest request) {
        return ApiResponse.ok(postService.generate(request));
    }

    @PostMapping("/approve")
    public ApiResponse<PostRecord> approve(@RequestBody Map<String, Long> request) {
        return ApiResponse.ok(postService.approve(request.get("id")));
    }

    @PostMapping("/reject")
    public ApiResponse<PostRecord> reject(@RequestBody Map<String, Long> request) {
        return ApiResponse.ok(postService.reject(request.get("id")));
    }
}
