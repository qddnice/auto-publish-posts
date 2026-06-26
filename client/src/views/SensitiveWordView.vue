<template>
  <section class="page">
    <h1 class="page-title">违禁词</h1>
    <div class="panel">
      <el-table :data="words">
        <el-table-column prop="word" label="违禁词" min-width="220" />
        <el-table-column prop="matchType" label="匹配方式" width="140" />
        <el-table-column prop="scope" label="范围" width="120" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.active ? 'success' : 'info'">{{ row.active ? '启用' : '停用' }}</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </section>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { fetchSensitiveWords } from '@/api/modules'
import type { SensitiveWordRule } from '@/api/types'

const words = ref<SensitiveWordRule[]>([])

onMounted(async () => {
  words.value = await fetchSensitiveWords()
})
</script>
