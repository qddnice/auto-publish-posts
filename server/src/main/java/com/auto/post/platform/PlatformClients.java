package com.auto.post.platform;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

/**
 * 外部平台 Client 集合，封装 FB、TT、AI 工作流、钉钉通知的 HTTP 调用边界。
 */
@Component
public class PlatformClients {

    private final RestClient restClient;

    public PlatformClients(RestClient restClient) {
        this.restClient = restClient;
    }

    public Map<String, Object> callAiWorkflow(String endpoint, Map<String, Object> payload) {
        if (endpoint == null || endpoint.isBlank()) {
            return Map.of("success", false, "reason", "AI 工作流地址未配置");
        }
        return castMap(restClient.post().uri(endpoint).body(payload).retrieve().body(Map.class));
    }

    public String publishFbFeed(String endpoint, Map<String, Object> payload) {
        if (endpoint == null || endpoint.isBlank()) {
            return "MOCK_FB_POST_ID";
        }
        Map<?, ?> response = restClient.post().uri(endpoint).body(payload).retrieve().body(Map.class);
        return response == null ? null : String.valueOf(response.get("id"));
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> castMap(Map<?, ?> raw) {
        return raw == null ? Map.of() : (Map<String, Object>) raw;
    }
}
