package com.auto.post.asset.mapper;

import com.auto.post.asset.entity.TtAdvertiserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ap_tt_advertiser 表访问接口，用于查询 TT advertiser 下拉选项。
 */
@Mapper
public interface TtAdvertiserMapper extends BaseMapper<TtAdvertiserEntity> {
}
