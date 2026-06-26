package com.auto.post.material;

/**
 * 素材库响应/保存对象，用于素材库列表和编辑。
 *
 * @param id 素材库主键
 * @param name 素材库名称
 * @param type 素材库类型，COMMON/BRAND
 * @param brandName 品牌名称，品牌素材库使用
 * @param language 素材语言
 * @param status 素材库状态
 * @param materialCount 素材数量
 */
public record MaterialLibraryRecord(Long id, String name, String type, String brandName,
                                    String language, String status, int materialCount) {
}
