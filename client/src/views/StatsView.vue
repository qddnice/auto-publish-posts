<template>
  <section class="page">
    <h1 class="page-title">数据统计</h1>
    <div class="metric-grid">
      <div class="metric"><label>今日回复</label><strong>{{ summary.todayReplyCount }}</strong></div>
      <div class="metric"><label>待人工</label><strong>{{ summary.manualPendingCount }}</strong></div>
      <div class="metric"><label>今日发帖</label><strong>{{ summary.todayPostCount }}</strong></div>
      <div class="metric"><label>违禁词命中</label><strong>{{ summary.sensitiveHitCount }}</strong></div>
    </div>
    <div class="panel">
      <el-table :data="trend">
        <el-table-column prop="date" label="日期" />
        <el-table-column prop="FB" label="FB 回复量" />
        <el-table-column prop="TT" label="TT 回复量" />
      </el-table>
    </div>
  </section>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { getData } from '@/api/http'

const summary = ref({ todayReplyCount: 0, manualPendingCount: 0, todayPostCount: 0, sensitiveHitCount: 0 })
const trend = ref<Array<Record<string, string | number>>>([])

onMounted(async () => {
  summary.value = await getData('/api/stats/summary', {
    todayReplyCount: 1,
    manualPendingCount: 1,
    todayPostCount: 0,
    sensitiveHitCount: 0,
  })
  trend.value = await getData('/api/stats/trend', [
    { date: '2026-06-26', FB: 9, TT: 3 },
  ])
})
</script>
