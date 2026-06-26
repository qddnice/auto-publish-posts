package com.auto.post.asset.mapper;

import com.auto.post.asset.entity.FbPageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ap_fb_page 表访问接口，用于查询 FB Page 下拉选项。
 */
@Mapper
public interface FbPageMapper extends BaseMapper<FbPageEntity> {
}
