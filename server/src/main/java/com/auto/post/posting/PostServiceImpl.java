package com.auto.post.posting;

import com.auto.post.common.BizException;
import com.auto.post.material.entity.MaterialEntity;
import com.auto.post.material.mapper.MaterialMapper;
import com.auto.post.posting.entity.PostRecordEntity;
import com.auto.post.posting.mapper.PostRecordMapper;
import com.auto.post.posting.model.MaterialCandidate;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * 基于 ap_post_record 和 ap_material 的 FB 自动发帖服务实现。
 */
@Service
public class PostServiceImpl implements PostService {

    private final PostRecordMapper postRecordMapper;
    private final MaterialMapper materialMapper;
    private final MaterialSelector materialSelector;

    public PostServiceImpl(PostRecordMapper postRecordMapper,
                           MaterialMapper materialMapper,
                           MaterialSelector materialSelector) {
        this.postRecordMapper = postRecordMapper;
        this.materialMapper = materialMapper;
        this.materialSelector = materialSelector;
    }

    @Override
    public List<PostRecord> listRecords() {
        return postRecordMapper.selectList(Wrappers.<PostRecordEntity>lambdaQuery()
                        .eq(PostRecordEntity::getDeleted, false)
                        .orderByDesc(PostRecordEntity::getScheduledAt))
                .stream().map(this::toRecord).toList();
    }

    @Override
    public PostRecord generate(GeneratePostRequest request) {
        List<MaterialEntity> materials = materialMapper.selectForList(request.libraryId());
        MaterialCandidate selected = materialSelector.select(materials.stream()
                .map(item -> new MaterialCandidate(item.getId(), "ACTIVE".equals(item.getStatus()),
                        item.getLastUsedAt(), item.getUsedCount() == null ? 0 : item.getUsedCount()))
                .toList(), OffsetDateTime.now());
        MaterialEntity material = materials.stream()
                .filter(item -> item.getId().equals(selected.id()))
                .findFirst()
                .orElseThrow(() -> new BizException("素材不存在"));
        String initialStatus = "MANUAL".equals(request.reviewMode()) ? "PENDING_REVIEW" : "SCHEDULED";
        PostRecordEntity entity = new PostRecordEntity();
        entity.setPageId(request.pageId());
        entity.setPageName(request.pageName());
        entity.setLibraryId(request.libraryId());
        entity.setMaterialId(material.getId());
        entity.setPostContent(material.getTextContent());
        entity.setPostImageUrl(material.getImageUrl());
        entity.setStatus(initialStatus);
        entity.setReviewMode(request.reviewMode());
        entity.setScheduledAt(request.scheduledAt());
        entity.setRetryCount(0);
        entity.setVersion(0);
        entity.setDeleted(false);
        postRecordMapper.insert(entity);
        return toRecord(entity);
    }

    @Override
    public PostRecord approve(Long id) {
        return transfer(id, "APPROVED");
    }

    @Override
    public PostRecord reject(Long id) {
        return transfer(id, "REJECTED");
    }

    private PostRecord transfer(Long id, String target) {
        PostRecordEntity entity = postRecordMapper.selectById(id);
        if (entity == null || Boolean.TRUE.equals(entity.getDeleted())) {
            throw new BizException("发帖记录不存在");
        }
        if (!PostStatusMachine.canTransfer(entity.getStatus(), target)) {
            throw new BizException("当前状态不允许流转到 " + target);
        }
        entity.setStatus(target);
        entity.setReviewedAt(OffsetDateTime.now());
        entity.setUpdatedAt(OffsetDateTime.now());
        postRecordMapper.updateById(entity);
        return toRecord(entity);
    }

    private PostRecord toRecord(PostRecordEntity entity) {
        return new PostRecord(entity.getId(), entity.getPageId(), entity.getPageName(), entity.getMaterialId(),
                entity.getPostContent(), entity.getPostImageUrl(), entity.getStatus(), entity.getReviewMode(),
                entity.getScheduledAt(), entity.getPublishedAt(), entity.getFailReason());
    }
}
