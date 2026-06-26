<template>
  <section class="page">
    <div class="toolbar">
      <h1 class="page-title">运营工作台</h1>
      <el-segmented v-model="platform" :options="['全部', 'FB', 'TT']" />
    </div>
    <div class="reply-list">
      <article v-for="item in visibleMessages" :key="item.id" class="reply-card">
        <div class="reply-head">
          <strong>{{ item.sourceName }} · {{ item.messageType }}</strong>
          <span>
            <el-tag v-if="item.needsReview" type="warning">待复查</el-tag>
            <el-tag :type="statusType(item.replyStatus)">{{ statusText(item.replyStatus) }}</el-tag>
          </span>
        </div>
        <div class="bubble-row">
          <div class="bubble">{{ item.originalContent }}</div>
          <div class="bubble reply">{{ item.replyContent || '等待处理' }}</div>
        </div>
        <div class="reply-actions">
          <span>{{ item.platform }} · {{ item.replySource || '未回复' }}</span>
          <el-button size="small" :icon="EditPen" @click="openManualReply(item)">人工回复</el-button>
        </div>
      </article>
    </div>
    <el-dialog v-model="manualDialogVisible" title="人工回复" width="520px">
      <el-input v-model="manualText" type="textarea" :rows="5" />
      <template #footer>
        <el-button @click="manualDialogVisible = false">取消</el-button>
        <el-button type="primary" :icon="Promotion" @click="manualDialogVisible = false">发送</el-button>
      </template>
    </el-dialog>
  </section>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { EditPen, Promotion } from '@element-plus/icons-vue'
import { fetchMessages } from '@/api/modules'
import type { MessageRecord } from '@/api/types'
import { statusText, statusType } from '@/utils/status'

const messages = ref<MessageRecord[]>([])
const platform = ref('全部')
const manualDialogVisible = ref(false)
const manualText = ref('')

const visibleMessages = computed(() =>
  platform.value === '全部' ? messages.value : messages.value.filter((item) => item.platform === platform.value),
)

function openManualReply(item: MessageRecord) {
  manualText.value = item.replyContent
  manualDialogVisible.value = true
}

onMounted(async () => {
  messages.value = await fetchMessages()
})
</script>
