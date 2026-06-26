<template>
  <el-container class="app-shell">
    <el-aside width="236px" class="sidebar">
      <div class="brand">
        <el-icon><Promotion /></el-icon>
        <span>auto-publish-posts</span>
      </div>
      <el-menu router :default-active="$route.path" class="nav-menu">
        <el-menu-item index="/dashboard"><el-icon><DataBoard /></el-icon><span>总览</span></el-menu-item>
        <el-menu-item index="/config"><el-icon><Setting /></el-icon><span>自动化配置</span></el-menu-item>
        <el-menu-item index="/reply"><el-icon><ChatDotRound /></el-icon><span>运营工作台</span></el-menu-item>
        <el-menu-item index="/content"><el-icon><Picture /></el-icon><span>素材与发帖</span></el-menu-item>
        <el-menu-item index="/sensitive-word"><el-icon><Warning /></el-icon><span>违禁词</span></el-menu-item>
        <el-menu-item index="/stats"><el-icon><TrendCharts /></el-icon><span>数据统计</span></el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="topbar">
        <div>
          <strong>资产运营自动化</strong>
          <span>FB/TT 智能回复 · FB 自动发帖</span>
        </div>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import { ChatDotRound, DataBoard, Picture, Promotion, Setting, TrendCharts, Warning } from '@element-plus/icons-vue'
</script>
