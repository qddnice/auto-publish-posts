<template>
  <section class="page">
    <h1 class="page-title">素材与发帖</h1>
    <el-tabs>
      <el-tab-pane label="素材库">
        <div class="panel">
          <el-table :data="libraries">
            <el-table-column prop="name" label="素材库" min-width="180" />
            <el-table-column prop="type" label="类型" width="120" />
            <el-table-column prop="language" label="语言" width="100" />
            <el-table-column prop="materialCount" label="素材数" width="100" />
            <el-table-column label="状态" width="100"><template #default="{ row }"><el-tag :type="statusType(row.status)">{{ statusText(row.status) }}</el-tag></template></el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane label="素材">
        <div class="panel">
          <el-table :data="materials">
            <el-table-column label="图片" width="100"><template #default="{ row }"><img class="media-thumb" :src="row.imageUrl" alt="" /></template></el-table-column>
            <el-table-column prop="textContent" label="文案" min-width="220" />
            <el-table-column prop="type" label="类型" width="120" />
            <el-table-column label="标签" width="180"><template #default="{ row }"><el-tag v-for="tag in row.tags" :key="tag" size="small">{{ tag }}</el-tag></template></el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane label="发帖记录">
        <div class="panel">
          <el-table :data="posts">
            <el-table-column label="内容" min-width="260">
              <template #default="{ row }">
                <div style="display:flex;gap:10px;align-items:center">
                  <img class="media-thumb" :src="row.postImageUrl" alt="" />
                  <span>{{ row.postContent }}</span>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="pageName" label="主页" min-width="160" />
            <el-table-column prop="scheduledAt" label="计划时间" min-width="180" />
            <el-table-column label="状态" width="110"><template #default="{ row }"><el-tag :type="statusType(row.status)">{{ statusText(row.status) }}</el-tag></template></el-table-column>
            <el-table-column label="操作" width="120"><template #default="{ row }"><el-button size="small" :icon="Check" @click="approve(row.id)">通过</el-button></template></el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
  </section>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { Check } from '@element-plus/icons-vue'
import { approvePost, fetchLibraries, fetchMaterials, fetchPosts } from '@/api/modules'
import type { Material, MaterialLibrary, PostRecord } from '@/api/types'
import { statusText, statusType } from '@/utils/status'

const libraries = ref<MaterialLibrary[]>([])
const materials = ref<Material[]>([])
const posts = ref<PostRecord[]>([])

async function approve(id: number) {
  const updated = await approvePost(id)
  posts.value = posts.value.map((item) => (item.id === updated.id ? updated : item))
}

onMounted(async () => {
  ;[libraries.value, materials.value, posts.value] = await Promise.all([fetchLibraries(), fetchMaterials(), fetchPosts()])
})
</script>
