package com.auto.post.material;

import com.auto.post.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 素材库与素材管理接口。
 */
@RestController
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/library/list")
    public ApiResponse<List<MaterialLibraryRecord>> libraries() {
        return ApiResponse.ok(materialService.listLibraries());
    }

    @PostMapping("/library/save")
    public ApiResponse<MaterialLibraryRecord> saveLibrary(@RequestBody MaterialLibraryRecord request) {
        return ApiResponse.ok(materialService.saveLibrary(request));
    }

    @GetMapping("/list")
    public ApiResponse<List<MaterialRecord>> materials(@RequestParam(required = false) Long libraryId) {
        return ApiResponse.ok(materialService.listMaterials(libraryId));
    }

    @PostMapping("/save")
    public ApiResponse<MaterialRecord> saveMaterial(@RequestBody MaterialRecord request) {
        return ApiResponse.ok(materialService.saveMaterial(request));
    }
}
