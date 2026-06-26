package com.auto.post.reply;

import com.auto.post.reply.model.ConfidenceResult;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * AI 回复置信度计算器，按 PRD 使用 AI 分值叠加规则修正。
 */
@Component
public class ConfidenceCalculator {

    public ConfidenceResult calculate(double aiScore, String originalContent, boolean symbolOnly, boolean hasProductInfo) {
        double score = aiScore;
        String safeContent = originalContent == null ? "" : originalContent.trim();
        if (safeContent.length() < 5) {
            score -= 0.2;
        }
        if (symbolOnly || safeContent.matches("^[\\p{Punct}\\p{S}\\s]+$")) {
            score -= 0.3;
        }
        if (hasProductInfo) {
            score += 0.1;
        }
        double finalScore = BigDecimal.valueOf(Math.max(0, Math.min(1, score)))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        if (finalScore >= 0.8) {
            return new ConfidenceResult("HIGH", finalScore, true, false);
        }
        if (finalScore >= 0.5) {
            return new ConfidenceResult("MEDIUM", finalScore, true, true);
        }
        return new ConfidenceResult("LOW", finalScore, false, false);
    }
}
