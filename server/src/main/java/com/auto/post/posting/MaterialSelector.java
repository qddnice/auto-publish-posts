package com.auto.post.posting;

import com.auto.post.common.BizException;
import com.auto.post.posting.model.MaterialCandidate;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * FB 自动发帖素材选择器，优先选择 7 天内未使用且最久未使用的素材。
 */
@Component
public class MaterialSelector {

    private static final int DEDUP_DAYS = 7;

    public MaterialCandidate select(List<MaterialCandidate> candidates, OffsetDateTime now) {
        List<MaterialCandidate> active = candidates.stream()
                .filter(MaterialCandidate::active)
                .toList();
        if (active.isEmpty()) {
            throw new BizException("没有可用素材");
        }
        return active.stream()
                .filter(candidate -> candidate.lastUsedAt() == null
                        || candidate.lastUsedAt().isBefore(now.minusDays(DEDUP_DAYS)))
                .min(compareOldest())
                .orElseGet(() -> active.stream().min(compareOldest()).orElseThrow());
    }

    private Comparator<MaterialCandidate> compareOldest() {
        return Comparator
                .comparing((MaterialCandidate c) -> c.lastUsedAt() == null ? OffsetDateTime.MIN : c.lastUsedAt())
                .thenComparingInt(MaterialCandidate::usedCount);
    }
}
