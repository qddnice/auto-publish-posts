package com.auto.post.configuration;

import com.auto.post.configuration.entity.EmergencyStopLogEntity;
import com.auto.post.configuration.entity.FbConfigEntity;
import com.auto.post.configuration.entity.TtConfigEntity;
import com.auto.post.configuration.mapper.EmergencyStopLogMapper;
import com.auto.post.configuration.mapper.FbConfigMapper;
import com.auto.post.configuration.mapper.TtConfigMapper;
import com.auto.post.posting.entity.PostRecordEntity;
import com.auto.post.posting.mapper.PostRecordMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/**
 * 基于 ap_fb_config 和 ap_tt_config 的运营配置服务实现。
 */
@Service
public class OperationConfigServiceImpl implements OperationConfigService {

    private final FbConfigMapper fbConfigMapper;
    private final TtConfigMapper ttConfigMapper;
    private final PostRecordMapper postRecordMapper;
    private final EmergencyStopLogMapper emergencyStopLogMapper;

    public OperationConfigServiceImpl(FbConfigMapper fbConfigMapper,
                                      TtConfigMapper ttConfigMapper,
                                      PostRecordMapper postRecordMapper,
                                      EmergencyStopLogMapper emergencyStopLogMapper) {
        this.fbConfigMapper = fbConfigMapper;
        this.ttConfigMapper = ttConfigMapper;
        this.postRecordMapper = postRecordMapper;
        this.emergencyStopLogMapper = emergencyStopLogMapper;
    }

    @Override
    public List<FbConfigRecord> listFbConfigs() {
        return fbConfigMapper.selectList(Wrappers.<FbConfigEntity>lambdaQuery()
                        .eq(FbConfigEntity::getDeleted, false)
                        .orderByDesc(FbConfigEntity::getUpdatedAt))
                .stream().map(this::toRecord).toList();
    }

    @Override
    public FbConfigRecord saveFbConfig(FbConfigRecord request) {
        FbConfigEntity entity = toEntity(request);
        if (entity.getId() == null) {
            fbConfigMapper.insert(entity);
        } else {
            entity.setUpdatedAt(OffsetDateTime.now());
            fbConfigMapper.updateById(entity);
        }
        return toRecord(entity);
    }

    @Override
    public List<TtConfigRecord> listTtConfigs() {
        return ttConfigMapper.selectList(Wrappers.<TtConfigEntity>lambdaQuery()
                        .eq(TtConfigEntity::getDeleted, false)
                        .orderByDesc(TtConfigEntity::getUpdatedAt))
                .stream().map(this::toRecord).toList();
    }

    @Override
    public TtConfigRecord saveTtConfig(TtConfigRecord request) {
        TtConfigEntity entity = toEntity(request);
        if (entity.getId() == null) {
            ttConfigMapper.insert(entity);
        } else {
            entity.setUpdatedAt(OffsetDateTime.now());
            ttConfigMapper.updateById(entity);
        }
        return toRecord(entity);
    }

    @Override
    public Map<String, Long> graySummary() {
        long fbTotal = fbConfigMapper.selectCount(Wrappers.<FbConfigEntity>lambdaQuery().eq(FbConfigEntity::getDeleted, false));
        long ttTotal = ttConfigMapper.selectCount(Wrappers.<TtConfigEntity>lambdaQuery().eq(TtConfigEntity::getDeleted, false));
        long fbEnabled = fbConfigMapper.selectCount(Wrappers.<FbConfigEntity>lambdaQuery()
                .eq(FbConfigEntity::getDeleted, false).eq(FbConfigEntity::getStatus, "ENABLED"));
        long ttEnabled = ttConfigMapper.selectCount(Wrappers.<TtConfigEntity>lambdaQuery()
                .eq(TtConfigEntity::getDeleted, false).eq(TtConfigEntity::getStatus, "ENABLED"));
        long fbPaused = fbConfigMapper.selectCount(Wrappers.<FbConfigEntity>lambdaQuery()
                .eq(FbConfigEntity::getDeleted, false).eq(FbConfigEntity::getStatus, "PAUSED"));
        long ttPaused = ttConfigMapper.selectCount(Wrappers.<TtConfigEntity>lambdaQuery()
                .eq(TtConfigEntity::getDeleted, false).eq(TtConfigEntity::getStatus, "PAUSED"));
        return Map.of("total", fbTotal + ttTotal, "enabled", fbEnabled + ttEnabled, "paused", fbPaused + ttPaused);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Integer> emergencyStop() {
        List<FbConfigEntity> fbConfigs = fbConfigMapper.selectList(Wrappers.<FbConfigEntity>lambdaQuery()
                .eq(FbConfigEntity::getDeleted, false).ne(FbConfigEntity::getStatus, "PAUSED"));
        List<TtConfigEntity> ttConfigs = ttConfigMapper.selectList(Wrappers.<TtConfigEntity>lambdaQuery()
                .eq(TtConfigEntity::getDeleted, false).ne(TtConfigEntity::getStatus, "PAUSED"));
        fbConfigs.forEach(item -> {
            item.setStatus("PAUSED");
            item.setUpdatedAt(OffsetDateTime.now());
            fbConfigMapper.updateById(item);
        });
        ttConfigs.forEach(item -> {
            item.setStatus("PAUSED");
            item.setUpdatedAt(OffsetDateTime.now());
            ttConfigMapper.updateById(item);
        });

        List<PostRecordEntity> cancellablePosts = postRecordMapper.selectList(Wrappers.<PostRecordEntity>lambdaQuery()
                .eq(PostRecordEntity::getDeleted, false)
                .in(PostRecordEntity::getStatus, List.of("PENDING_REVIEW", "APPROVED", "SCHEDULED")));
        cancellablePosts.forEach(item -> {
            item.setStatus("CANCELLED");
            item.setUpdatedAt(OffsetDateTime.now());
            postRecordMapper.updateById(item);
        });

        EmergencyStopLogEntity log = new EmergencyStopLogEntity();
        log.setOperatorName("system");
        log.setScopeDesc("ALL");
        log.setAffectedConfigCount(fbConfigs.size() + ttConfigs.size());
        log.setCancelledTaskCount(cancellablePosts.size());
        emergencyStopLogMapper.insert(log);
        return Map.of("affectedConfigCount", log.getAffectedConfigCount(), "cancelledTaskCount", log.getCancelledTaskCount());
    }

    private FbConfigRecord toRecord(FbConfigEntity entity) {
        return new FbConfigRecord(entity.getId(), entity.getPageId(), entity.getPageName(),
                Boolean.TRUE.equals(entity.getEnableReply()), Boolean.TRUE.equals(entity.getEnablePost()),
                valueOrDefault(entity.getReplyIntervalMin(), 5), valueOrDefault(entity.getDailyReplyLimit(), 100),
                entity.getPostReviewMode(), entity.getStatus());
    }

    private FbConfigEntity toEntity(FbConfigRecord record) {
        FbConfigEntity entity = new FbConfigEntity();
        entity.setId(record.id());
        entity.setPageId(record.pageId());
        entity.setPageName(record.pageName());
        entity.setEnableReply(record.enableReply());
        entity.setEnablePost(record.enablePost());
        entity.setReplyIntervalMin(record.replyIntervalMin());
        entity.setDailyReplyLimit(record.dailyReplyLimit());
        entity.setPostReviewMode(record.postReviewMode());
        entity.setStatus(record.status());
        entity.setDeleted(false);
        return entity;
    }

    private TtConfigRecord toRecord(TtConfigEntity entity) {
        return new TtConfigRecord(entity.getId(), entity.getCredentialId(), entity.getBcId(), entity.getBcName(),
                entity.getOrgId(), entity.getOrgName(), entity.getLinkedAdvertiserId(), entity.getLinkedAdvertiserName(),
                Boolean.TRUE.equals(entity.getEnableCommentReply()), Boolean.TRUE.equals(entity.getEnableDmReply()),
                valueOrDefault(entity.getReplyIntervalMin(), 5), valueOrDefault(entity.getDailyReplyLimit(), 100),
                entity.getStatus());
    }

    private TtConfigEntity toEntity(TtConfigRecord record) {
        TtConfigEntity entity = new TtConfigEntity();
        entity.setId(record.id());
        entity.setCredentialId(record.credentialId());
        entity.setBcId(record.bcId());
        entity.setBcName(record.bcName());
        entity.setOrgId(record.orgId());
        entity.setOrgName(record.orgName());
        entity.setLinkedAdvertiserId(record.linkedAdvertiserId());
        entity.setLinkedAdvertiserName(record.linkedAdvertiserName());
        entity.setEnableCommentReply(record.enableCommentReply());
        entity.setEnableDmReply(record.enableDmReply());
        entity.setReplyIntervalMin(record.replyIntervalMin());
        entity.setDailyReplyLimit(record.dailyReplyLimit());
        entity.setStatus(record.status());
        entity.setDeleted(false);
        return entity;
    }

    private int valueOrDefault(Integer value, int defaultValue) {
        return value == null ? defaultValue : value;
    }
}
