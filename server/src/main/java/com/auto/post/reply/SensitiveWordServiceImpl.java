package com.auto.post.reply;

import com.auto.post.reply.entity.SensitiveWordEntity;
import com.auto.post.reply.mapper.SensitiveWordMapper;
import com.auto.post.reply.model.SensitiveWordRule;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * 基于 ap_sensitive_word 的违禁词服务实现。
 */
@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {

    private final SensitiveWordMapper mapper;
    private final SensitiveWordDetector detector;

    public SensitiveWordServiceImpl(SensitiveWordMapper mapper, SensitiveWordDetector detector) {
        this.mapper = mapper;
        this.detector = detector;
    }

    @Override
    public List<SensitiveWordRule> listRules() {
        return mapper.selectList(Wrappers.<SensitiveWordEntity>lambdaQuery()
                        .eq(SensitiveWordEntity::getDeleted, false)
                        .orderByDesc(SensitiveWordEntity::getUpdatedAt))
                .stream().map(this::toRule).toList();
    }

    @Override
    public SensitiveWordRule saveRule(SensitiveWordRule rule) {
        SensitiveWordEntity existing = mapper.selectOne(Wrappers.<SensitiveWordEntity>lambdaQuery()
                .eq(SensitiveWordEntity::getDeleted, false)
                .eq(SensitiveWordEntity::getWord, rule.word())
                .eq(SensitiveWordEntity::getScope, rule.scope())
                .last("limit 1"));
        SensitiveWordEntity entity = existing == null ? new SensitiveWordEntity() : existing;
        entity.setWord(rule.word());
        entity.setMatchType(rule.matchType());
        entity.setScope(rule.scope());
        entity.setStatus(rule.active() ? "ACTIVE" : "DISABLED");
        entity.setDeleted(false);
        if (entity.getId() == null) {
            mapper.insert(entity);
        } else {
            entity.setUpdatedAt(OffsetDateTime.now());
            mapper.updateById(entity);
        }
        return toRule(entity);
    }

    @Override
    public SensitiveWordDetector.Result check(String platform, String content) {
        return detector.check(platform, content, listRules());
    }

    private SensitiveWordRule toRule(SensitiveWordEntity entity) {
        return new SensitiveWordRule(entity.getWord(), entity.getMatchType(), entity.getScope(),
                "ACTIVE".equals(entity.getStatus()));
    }
}
