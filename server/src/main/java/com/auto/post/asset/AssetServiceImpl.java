package com.auto.post.asset;

import com.auto.post.asset.entity.FbPageEntity;
import com.auto.post.asset.entity.TtAdvertiserEntity;
import com.auto.post.asset.entity.TtIdentityEntity;
import com.auto.post.asset.mapper.FbPageMapper;
import com.auto.post.asset.mapper.TtAdvertiserMapper;
import com.auto.post.asset.mapper.TtIdentityMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基于项目自有平台资产表的下拉查询实现。
 */
@Service
public class AssetServiceImpl implements AssetService {

    private static final String ACTIVE = "ACTIVE";

    private final FbPageMapper fbPageMapper;
    private final TtIdentityMapper ttIdentityMapper;
    private final TtAdvertiserMapper ttAdvertiserMapper;

    public AssetServiceImpl(FbPageMapper fbPageMapper,
                            TtIdentityMapper ttIdentityMapper,
                            TtAdvertiserMapper ttAdvertiserMapper) {
        this.fbPageMapper = fbPageMapper;
        this.ttIdentityMapper = ttIdentityMapper;
        this.ttAdvertiserMapper = ttAdvertiserMapper;
    }

    @Override
    public List<AssetOption> listFbPages() {
        return fbPageMapper.selectList(Wrappers.<FbPageEntity>lambdaQuery()
                        .eq(FbPageEntity::getDeleted, false)
                        .eq(FbPageEntity::getStatus, ACTIVE)
                        .orderByDesc(FbPageEntity::getUpdatedAt))
                .stream()
                .map(page -> new AssetOption(page.getPageId(), page.getPageName()))
                .toList();
    }

    @Override
    public List<AssetOption> listTtIdentities() {
        return ttIdentityMapper.selectList(Wrappers.<TtIdentityEntity>lambdaQuery()
                        .eq(TtIdentityEntity::getDeleted, false)
                        .eq(TtIdentityEntity::getStatus, ACTIVE)
                        .orderByDesc(TtIdentityEntity::getUpdatedAt))
                .stream()
                .map(identity -> new AssetOption(identity.getIdentityId(), identity.getIdentityName()))
                .toList();
    }

    @Override
    public List<AssetOption> listTtAdvertisers() {
        return ttAdvertiserMapper.selectList(Wrappers.<TtAdvertiserEntity>lambdaQuery()
                        .eq(TtAdvertiserEntity::getDeleted, false)
                        .eq(TtAdvertiserEntity::getStatus, ACTIVE)
                        .orderByDesc(TtAdvertiserEntity::getUpdatedAt))
                .stream()
                .map(advertiser -> new AssetOption(advertiser.getAdvertiserId(), advertiser.getAdvertiserName()))
                .toList();
    }
}
