package com.auto.post.reply;

import com.auto.post.reply.model.SensitiveWordRule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 违禁词检测器，用于回复发送前拦截不允许发送的文本。
 */
@Component
public class SensitiveWordDetector {

    private final List<SensitiveWordRule> defaultRules;

    public SensitiveWordDetector() {
        this.defaultRules = List.of();
    }

    public SensitiveWordDetector(List<SensitiveWordRule> defaultRules) {
        this.defaultRules = defaultRules;
    }

    public Result check(String platform, String content) {
        return check(platform, content, defaultRules);
    }

    public Result check(String platform, String content, List<SensitiveWordRule> rules) {
        String safeContent = content == null ? "" : content.toLowerCase(Locale.ROOT);
        List<String> hits = new ArrayList<>();
        for (SensitiveWordRule rule : rules) {
            if (!rule.active() || !scopeMatches(platform, rule.scope())) {
                continue;
            }
            String word = rule.word().toLowerCase(Locale.ROOT);
            boolean matched = "EXACT".equalsIgnoreCase(rule.matchType())
                    ? containsExactToken(safeContent, word)
                    : safeContent.contains(word);
            if (matched) {
                hits.add(rule.word());
            }
        }
        return new Result(!hits.isEmpty(), hits);
    }

    private boolean scopeMatches(String platform, String scope) {
        return "GLOBAL".equalsIgnoreCase(scope) || scope.equalsIgnoreCase(platform);
    }

    private boolean containsExactToken(String content, String word) {
        return List.of(content.split("\\s+")).contains(word);
    }

    /**
     * 违禁词检测结果。
     *
     * @param blocked 是否命中违禁词并需要阻止发送
     * @param hitWords 命中的违禁词列表
     */
    public record Result(boolean blocked, List<String> hitWords) {
    }
}
