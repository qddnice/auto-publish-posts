package com.auto.post.asset.mapper;

import com.auto.post.asset.entity.TtIdentityEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ap_tt_identity 表访问接口，用于查询 TT identity 下拉选项。
 */
@Mapper
public interface TtIdentityMapper extends BaseMapper<TtIdentityEntity> {
}
