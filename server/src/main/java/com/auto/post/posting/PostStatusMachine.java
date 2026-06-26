package com.auto.post.posting;

import java.util.Map;
import java.util.Set;

/**
 * FB 发帖记录状态机，限制审核和发布状态流转。
 */
public final class PostStatusMachine {

    private static final Map<String, Set<String>> TRANSFERS = Map.of(
            "PENDING_REVIEW", Set.of("APPROVED", "REJECTED"),
            "APPROVED", Set.of("PUBLISHED", "FAILED"),
            "SCHEDULED", Set.of("PUBLISHED", "FAILED"),
            "FAILED", Set.of("SCHEDULED", "PUBLISHED"),
            "REJECTED", Set.of(),
            "PUBLISHED", Set.of()
    );

    private PostStatusMachine() {
    }

    public static boolean canTransfer(String from, String to) {
        return TRANSFERS.getOrDefault(from, Set.of()).contains(to);
    }
}
