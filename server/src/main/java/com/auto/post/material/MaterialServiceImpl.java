package com.auto.post.material;

import com.auto.post.common.BizException;
import com.auto.post.material.entity.MaterialEntity;
import com.auto.post.material.entity.MaterialLibraryEntity;
import com.auto.post.material.mapper.MaterialLibraryMapper;
import com.auto.post.material.mapper.MaterialMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * 基于 ap_material_library 和 ap_material 的素材服务实现。
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialLibraryMapper libraryMapper;
    private final MaterialMapper materialMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MaterialServiceImpl(MaterialLibraryMapper libraryMapper, MaterialMapper materialMapper) {
        this.libraryMapper = libraryMapper;
        this.materialMapper = materialMapper;
    }

    @Override
    public List<MaterialLibraryRecord> listLibraries() {
        return libraryMapper.selectList(Wrappers.<MaterialLibraryEntity>lambdaQuery()
                        .eq(MaterialLibraryEntity::getDeleted, false)
                        .orderByDesc(MaterialLibraryEntity::getUpdatedAt))
                .stream().map(this::toRecord).toList();
    }

    @Override
    public MaterialLibraryRecord saveLibrary(MaterialLibraryRecord request) {
        MaterialLibraryEntity entity = toEntity(request);
        if (entity.getId() == null) {
            libraryMapper.insert(entity);
        } else {
            entity.setUpdatedAt(OffsetDateTime.now());
            libraryMapper.updateById(entity);
        }
        return toRecord(entity);
    }

    @Override
    public List<MaterialRecord> listMaterials(Long libraryId) {
        return materialMapper.selectForList(libraryId).stream().map(this::toRecord).toList();
    }

    @Override
    public MaterialRecord saveMaterial(MaterialRecord request) {
        MaterialEntity entity = toEntity(request);
        if (entity.getId() == null) {
            materialMapper.insertRecord(entity);
        } else {
            entity.setUpdatedAt(OffsetDateTime.now());
            materialMapper.updateRecord(entity);
        }
        return toRecord(entity);
    }

    private MaterialLibraryRecord toRecord(MaterialLibraryEntity entity) {
        return new MaterialLibraryRecord(entity.getId(), entity.getName(), entity.getType(), entity.getBrandName(),
                entity.getLanguage(), entity.getStatus(), entity.getMaterialCount() == null ? 0 : entity.getMaterialCount());
    }

    private MaterialLibraryEntity toEntity(MaterialLibraryRecord record) {
        MaterialLibraryEntity entity = new MaterialLibraryEntity();
        entity.setId(record.id());
        entity.setName(record.name());
        entity.setType(record.type());
        entity.setBrandName(record.brandName());
        entity.setLanguage(record.language());
        entity.setStatus(record.status());
        entity.setMaterialCount(record.materialCount());
        entity.setDeleted(false);
        return entity;
    }

    private MaterialRecord toRecord(MaterialEntity entity) {
        return new MaterialRecord(entity.getId(), entity.getLibraryId(), entity.getType(), entity.getImageUrl(),
                entity.getTextContent(), parseTags(entity.getTagsJson()), entity.getStatus(),
                entity.getUsedCount() == null ? 0 : entity.getUsedCount(), entity.getLastUsedAt());
    }

    private MaterialEntity toEntity(MaterialRecord record) {
        MaterialEntity entity = new MaterialEntity();
        entity.setId(record.id());
        entity.setLibraryId(record.libraryId());
        entity.setType(record.type());
        entity.setImageUrl(record.imageUrl());
        entity.setTextContent(record.textContent());
        entity.setTagsJson(writeTags(record.tags()));
        entity.setStatus(record.status());
        entity.setUsedCount(record.usedCount());
        entity.setLastUsedAt(record.lastUsedAt());
        entity.setDeleted(false);
        return entity;
    }

    private List<String> parseTags(String tagsJson) {
        if (tagsJson == null || tagsJson.isBlank()) {
            return List.of();
        }
        try {
            return objectMapper.readValue(tagsJson, new TypeReference<>() {
            });
        } catch (JsonProcessingException ex) {
            throw new BizException("素材标签 JSON 解析失败");
        }
    }

    private String writeTags(List<String> tags) {
        try {
            return objectMapper.writeValueAsString(tags == null ? List.of() : tags);
        } catch (JsonProcessingException ex) {
            throw new BizException("素材标签 JSON 序列化失败");
        }
    }
}
