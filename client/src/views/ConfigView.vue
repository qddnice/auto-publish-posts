<template>
  <section class="page">
    <div class="toolbar">
      <h1 class="page-title">自动化配置</h1>
      <el-button type="danger" :icon="SwitchButton">紧急停止</el-button>
    </div>
    <el-tabs>
      <el-tab-pane label="FB 主页">
        <div class="panel">
          <el-table :data="fbConfigs">
            <el-table-column prop="pageName" label="主页" min-width="180" />
            <el-table-column label="自动回复" width="120"><template #default="{ row }"><el-switch v-model="row.enableReply" /></template></el-table-column>
            <el-table-column label="自动发帖" width="120"><template #default="{ row }"><el-switch v-model="row.enablePost" /></template></el-table-column>
            <el-table-column prop="replyIntervalMin" label="间隔(分钟)" width="120" />
            <el-table-column prop="dailyReplyLimit" label="日上限" width="100" />
            <el-table-column prop="postReviewMode" label="发帖审核" width="120" />
            <el-table-column label="状态" width="110">
              <template #default="{ row }"><el-tag :type="statusType(row.status)">{{ statusText(row.status) }}</el-tag></template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane label="TT 账号">
        <div class="panel">
          <el-table :data="ttConfigs">
            <el-table-column prop="orgName" label="TT账号" min-width="180" />
            <el-table-column prop="bcName" label="BC" min-width="140" />
            <el-table-column prop="linkedAdvertiserName" label="关联广告账户" min-width="170" />
            <el-table-column label="评论回复" width="120"><template #default="{ row }"><el-switch v-model="row.enableCommentReply" /></template></el-table-column>
            <el-table-column label="私信回复" width="120"><template #default="{ row }"><el-switch v-model="row.enableDmReply" /></template></el-table-column>
            <el-table-column label="状态" width="110">
              <template #default="{ row }"><el-tag :type="statusType(row.status)">{{ statusText(row.status) }}</el-tag></template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
  </section>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { SwitchButton } from '@element-plus/icons-vue'
import { fetchFbConfigs, fetchTtConfigs } from '@/api/modules'
import type { FbConfig, TtConfig } from '@/api/types'
import { statusText, statusType } from '@/utils/status'

const fbConfigs = ref<FbConfig[]>([])
const ttConfigs = ref<TtConfig[]>([])

onMounted(async () => {
  ;[fbConfigs.value, ttConfigs.value] = await Promise.all([fetchFbConfigs(), fetchTtConfigs()])
})
</script>
