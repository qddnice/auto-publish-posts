package com.auto.post.material;

import java.util.List;

/**
 * 素材库和素材管理服务。
 */
public interface MaterialService {

    /**
     * 查询素材库列表。
     *
     * @return 素材库列表
     */
    List<MaterialLibraryRecord> listLibraries();

    /**
     * 保存素材库，新增或按 ID 更新。
     *
     * @param request 素材库保存参数
     * @return 保存后的素材库
     */
    MaterialLibraryRecord saveLibrary(MaterialLibraryRecord request);

    /**
     * 查询素材列表，可按素材库过滤。
     *
     * @param libraryId 素材库 ID，可为空
     * @return 素材列表
     */
    List<MaterialRecord> listMaterials(Long libraryId);

    /**
     * 保存素材，新增或按 ID 更新。
     *
     * @param request 素材保存参数
     * @return 保存后的素材
     */
    MaterialRecord saveMaterial(MaterialRecord request);
}
