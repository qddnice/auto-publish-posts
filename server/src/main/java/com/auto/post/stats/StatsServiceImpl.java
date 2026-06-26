package com.auto.post.stats;

import com.auto.post.posting.entity.PostRecordEntity;
import com.auto.post.posting.mapper.PostRecordMapper;
import com.auto.post.reply.entity.MessageRecordEntity;
import com.auto.post.reply.entity.SensitiveWordEntity;
import com.auto.post.reply.mapper.MessageRecordMapper;
import com.auto.post.reply.mapper.SensitiveWordMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基于回复、发帖和违禁词表的统计服务实现。
 */
@Service
public class StatsServiceImpl implements StatsService {

    private final MessageRecordMapper messageRecordMapper;
    private final PostRecordMapper postRecordMapper;
    private final SensitiveWordMapper sensitiveWordMapper;

    public StatsServiceImpl(MessageRecordMapper messageRecordMapper,
                            PostRecordMapper postRecordMapper,
                            SensitiveWordMapper sensitiveWordMapper) {
        this.messageRecordMapper = messageRecordMapper;
        this.postRecordMapper = postRecordMapper;
        this.sensitiveWordMapper = sensitiveWordMapper;
    }

    @Override
    public StatsSummary summary() {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        OffsetDateTime start = today.atStartOfDay(ZoneId.of("Asia/Shanghai")).toOffsetDateTime();
        OffsetDateTime end = start.plusDays(1);
        long replied = messageRecordMapper.selectCount(Wrappers.<MessageRecordEntity>lambdaQuery()
                .eq(MessageRecordEntity::getDeleted, false)
                .eq(MessageRecordEntity::getReplyStatus, "REPLIED")
                .ge(MessageRecordEntity::getRepliedAt, start)
                .lt(MessageRecordEntity::getRepliedAt, end));
        long manual = messageRecordMapper.selectCount(Wrappers.<MessageRecordEntity>lambdaQuery()
                .eq(MessageRecordEntity::getDeleted, false)
                .eq(MessageRecordEntity::getReplyStatus, "MANUAL_REVIEW"));
        long published = postRecordMapper.selectCount(Wrappers.<PostRecordEntity>lambdaQuery()
                .eq(PostRecordEntity::getDeleted, false)
                .eq(PostRecordEntity::getStatus, "PUBLISHED")
                .ge(PostRecordEntity::getPublishedAt, start)
                .lt(PostRecordEntity::getPublishedAt, end));
        long sensitive = sensitiveWordMapper.selectCount(Wrappers.<SensitiveWordEntity>lambdaQuery()
                .eq(SensitiveWordEntity::getDeleted, false)
                .eq(SensitiveWordEntity::getStatus, "ACTIVE"));
        return new StatsSummary(replied, manual, published, sensitive);
    }

    @Override
    public List<Map<String, Object>> trend() {
        List<MessageRecordEntity> messages = messageRecordMapper.selectList(Wrappers.<MessageRecordEntity>lambdaQuery()
                .eq(MessageRecordEntity::getDeleted, false)
                .isNotNull(MessageRecordEntity::getCreatedAt)
                .orderByAsc(MessageRecordEntity::getCreatedAt));
        Map<LocalDate, Map<String, Object>> byDate = new TreeMap<>();
        for (MessageRecordEntity message : messages) {
            LocalDate date = message.getCreatedAt().toLocalDate();
            Map<String, Object> row = byDate.computeIfAbsent(date, key -> {
                Map<String, Object> value = new LinkedHashMap<>();
                value.put("date", key.toString());
                value.put("FB", 0L);
                value.put("TT", 0L);
                return value;
            });
            String platform = "TT".equals(message.getPlatform()) ? "TT" : "FB";
            row.put(platform, ((Long) row.get(platform)) + 1L);
        }
        return byDate.values().stream()
                .sorted(Comparator.comparing(item -> (String) item.get("date")))
                .toList();
    }
}
