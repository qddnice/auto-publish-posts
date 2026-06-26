<template>
  <section class="page">
    <h1 class="page-title">总览</h1>
    <div class="metric-grid">
      <div class="metric"><label>已配置</label><strong>{{ totalConfigs }}</strong></div>
      <div class="metric"><label>启用中</label><strong>{{ enabledConfigs }}</strong></div>
      <div class="metric"><label>待人工</label><strong>{{ manualPending }}</strong></div>
      <div class="metric"><label>待发帖审核</label><strong>{{ pendingPosts }}</strong></div>
    </div>
    <div class="panel">
      <el-table :data="messages" height="360">
        <el-table-column prop="platform" label="平台" width="100" />
        <el-table-column prop="sourceName" label="主页/账号" min-width="160" />
        <el-table-column prop="originalContent" label="最新消息" min-width="260" />
        <el-table-column label="状态" width="120">
          <template #default="{ row }">
            <el-tag :type="statusType(row.replyStatus)">{{ statusText(row.replyStatus) }}</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </section>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { fetchFbConfigs, fetchMessages, fetchPosts, fetchTtConfigs } from '@/api/modules'
import type { FbConfig, MessageRecord, PostRecord, TtConfig } from '@/api/types'
import { statusText, statusType } from '@/utils/status'

const fbConfigs = ref<FbConfig[]>([])
const ttConfigs = ref<TtConfig[]>([])
const messages = ref<MessageRecord[]>([])
const posts = ref<PostRecord[]>([])

const totalConfigs = computed(() => fbConfigs.value.length + ttConfigs.value.length)
const enabledConfigs = computed(
  () => fbConfigs.value.filter((item) => item.status === 'ENABLED').length + ttConfigs.value.filter((item) => item.status === 'ENABLED').length,
)
const manualPending = computed(() => messages.value.filter((item) => item.replyStatus === 'MANUAL_REVIEW').length)
const pendingPosts = computed(() => posts.value.filter((item) => item.status === 'PENDING_REVIEW').length)

onMounted(async () => {
  ;[fbConfigs.value, ttConfigs.value, messages.value, posts.value] = await Promise.all([
    fetchFbConfigs(),
    fetchTtConfigs(),
    fetchMessages(),
    fetchPosts(),
  ])
})
</script>
