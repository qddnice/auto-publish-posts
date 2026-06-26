package com.auto.post.credential;

import java.time.OffsetDateTime;

/**
 * 平台凭证 DTO，用于凭证列表和保存接口。
 *
 * @param id 凭证 ID
 * @param platform 平台编码 FB/TT
 * @param name 凭证名称
 * @param accessTokenMasked 脱敏后的访问令牌，仅用于页面展示
 * @param status 凭证状态 VALID/EXPIRED/ERROR
 * @param updatedAt 更新时间
 */
public record CredentialDTO(String id, String platform, String name, String accessTokenMasked,
                            String status, OffsetDateTime updatedAt) {
}
