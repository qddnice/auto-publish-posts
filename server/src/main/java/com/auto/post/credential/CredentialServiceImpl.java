package com.auto.post.credential;

import com.auto.post.credential.entity.PlatformCredentialEntity;
import com.auto.post.credential.mapper.PlatformCredentialMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * 基于 ap_platform_credential 的平台凭证服务实现。
 */
@Service
public class CredentialServiceImpl implements CredentialService {

    private final PlatformCredentialMapper credentialMapper;

    public CredentialServiceImpl(PlatformCredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    @Override
    public List<CredentialDTO> list(String platform) {
        return credentialMapper.selectList(Wrappers.<PlatformCredentialEntity>lambdaQuery()
                        .eq(PlatformCredentialEntity::getDeleted, false)
                        .eq(platform != null && !platform.isBlank(), PlatformCredentialEntity::getPlatform, platform)
                        .orderByDesc(PlatformCredentialEntity::getUpdatedAt))
                .stream().map(this::toDto).toList();
    }

    @Override
    public CredentialDTO save(CredentialDTO request) {
        PlatformCredentialEntity entity = new PlatformCredentialEntity();
        if (request.id() != null && !request.id().isBlank()) {
            entity.setId(Long.valueOf(request.id()));
        }
        entity.setPlatform(request.platform());
        entity.setCredentialName(request.name());
        entity.setEncryptedAccessToken(request.accessTokenMasked());
        entity.setStatus(request.status() == null ? "VALID" : request.status());
        entity.setDeleted(false);
        if (entity.getId() == null) {
            credentialMapper.insert(entity);
        } else {
            entity.setUpdatedAt(OffsetDateTime.now());
            credentialMapper.updateById(entity);
        }
        return toDto(entity);
    }

    private CredentialDTO toDto(PlatformCredentialEntity entity) {
        return new CredentialDTO(entity.getId() == null ? null : String.valueOf(entity.getId()),
                entity.getPlatform(), entity.getCredentialName(), mask(entity.getEncryptedAccessToken()),
                entity.getStatus(), entity.getUpdatedAt());
    }

    private String mask(String token) {
        if (token == null || token.isBlank()) {
            return "";
        }
        if (token.length() <= 8) {
            return "****";
        }
        return token.substring(0, 4) + "..." + token.substring(token.length() - 4);
    }
}
