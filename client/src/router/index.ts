import { createRouter, createWebHistory } from 'vue-router'
import DashboardView from '@/views/DashboardView.vue'
import ConfigView from '@/views/ConfigView.vue'
import ReplyWorkbenchView from '@/views/ReplyWorkbenchView.vue'
import ContentView from '@/views/ContentView.vue'
import SensitiveWordView from '@/views/SensitiveWordView.vue'
import StatsView from '@/views/StatsView.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/dashboard' },
    { path: '/dashboard', name: 'dashboard', component: DashboardView },
    { path: '/config', name: 'config', component: ConfigView },
    { path: '/reply', name: 'reply', component: ReplyWorkbenchView },
    { path: '/content', name: 'content', component: ContentView },
    { path: '/sensitive-word', name: 'sensitiveWord', component: SensitiveWordView },
    { path: '/stats', name: 'stats', component: StatsView },
  ],
})

export default router
