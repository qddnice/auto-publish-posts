package com.auto.post.material.mapper;

import com.auto.post.material.entity.MaterialEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * ap_material 表访问接口，包含 tags jsonb 字段的专用读写 SQL。
 */
@Mapper
public interface MaterialMapper extends BaseMapper<MaterialEntity> {

    /**
     * 查询素材列表，并将 tags jsonb 转为 JSON 字符串。
     *
     * @param libraryId 素材库 ID，可为空
     * @return 素材实体列表
     */
    @Select("""
            <script>
            select id, library_id, type, image_url, text_content, tags::text as tags_json,
                   status, used_count, last_used_at, updated_at, deleted
            from ap_material
            where deleted = false
            <if test="libraryId != null">
              and library_id = #{libraryId}
            </if>
            order by updated_at desc
            </script>
            """)
    List<MaterialEntity> selectForList(@Param("libraryId") Long libraryId);

    /**
     * 新增素材，写入 tags jsonb 字段。
     *
     * @param entity 素材实体
     * @return 影响行数
     */
    @Insert("""
            insert into ap_material (library_id, type, image_url, text_content, tags, status, used_count, last_used_at, deleted)
            values (#{libraryId}, #{type}, #{imageUrl}, #{textContent}, cast(#{tagsJson} as jsonb),
                    #{status}, #{usedCount}, #{lastUsedAt}, false)
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRecord(MaterialEntity entity);

    /**
     * 更新素材，写入 tags jsonb 字段。
     *
     * @param entity 素材实体
     * @return 影响行数
     */
    @Update("""
            update ap_material
            set library_id = #{libraryId},
                type = #{type},
                image_url = #{imageUrl},
                text_content = #{textContent},
                tags = cast(#{tagsJson} as jsonb),
                status = #{status},
                used_count = #{usedCount},
                last_used_at = #{lastUsedAt},
                updated_at = now()
            where id = #{id}
              and deleted = false
            """)
    int updateRecord(MaterialEntity entity);
}
