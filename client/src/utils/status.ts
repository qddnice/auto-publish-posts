import type { TagProps } from 'element-plus'

const STATUS_TEXT: Record<string, string> = {
  ENABLED: '已启用',
  DISABLED: '已停用',
  PAUSED: '已暂停',
  PENDING: '待处理',
  PROCESSING: '处理中',
  READY: '待发送',
  REPLIED: '已回复',
  MANUAL_REVIEW: '待人工',
  SKIPPED: '已跳过',
  FAILED: '失败',
  EXPIRED: '已过期',
  PENDING_REVIEW: '待审核',
  APPROVED: '已通过',
  REJECTED: '已拒绝',
  SCHEDULED: '待发布',
  PUBLISHED: '已发布',
  ACTIVE: '启用',
  INACTIVE: '停用',
}

const STATUS_TYPE: Record<string, TagProps['type']> = {
  ENABLED: 'success',
  ACTIVE: 'success',
  REPLIED: 'success',
  PUBLISHED: 'success',
  APPROVED: 'primary',
  READY: 'primary',
  SCHEDULED: 'primary',
  PENDING: 'primary',
  PROCESSING: 'primary',
  MANUAL_REVIEW: 'warning',
  PENDING_REVIEW: 'warning',
  PAUSED: 'warning',
  FAILED: 'danger',
  EXPIRED: 'info',
  SKIPPED: 'info',
  DISABLED: 'info',
  INACTIVE: 'info',
  REJECTED: 'info',
}

export function statusText(status: string | undefined): string {
  if (!status) return '-'
  return STATUS_TEXT[status] ?? status
}

export function statusType(status: string | undefined): TagProps['type'] {
  if (!status) return 'info'
  return STATUS_TYPE[status] ?? 'info'
}
