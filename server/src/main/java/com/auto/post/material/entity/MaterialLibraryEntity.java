package com.auto.post.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.OffsetDateTime;

/**
 * 素材库实体，对应 ap_material_library 表。
 */
@TableName("ap_material_library")
public class MaterialLibraryEntity {
    /** 素材库主键。 */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 素材库名称。 */
    private String name;
    /** 素材库类型。 */
    private String type;
    /** 品牌名称。 */
    private String brandName;
    /** 素材语言。 */
    private String language;
    /** 素材数量。 */
    private Integer materialCount;
    /** 素材库状态。 */
    private String status;
    /** 更新时间。 */
    private OffsetDateTime updatedAt;
    /** 逻辑删除标记。 */
    private Boolean deleted;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public Integer getMaterialCount() { return materialCount; }
    public void setMaterialCount(Integer materialCount) { this.materialCount = materialCount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}
