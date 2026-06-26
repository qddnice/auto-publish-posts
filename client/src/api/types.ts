export interface ApiResponse<T> {
  code: number
  message: string
  data: T
}

export interface FbConfig {
  id?: number
  pageId: string
  pageName: string
  enableReply: boolean
  enablePost: boolean
  replyIntervalMin: number
  dailyReplyLimit: number
  postReviewMode: 'MANUAL' | 'AUTO'
  status: string
}

export interface TtConfig {
  id?: number
  credentialId: string
  bcId: string
  bcName: string
  orgId: string
  orgName: string
  linkedAdvertiserId: string
  linkedAdvertiserName: string
  enableCommentReply: boolean
  enableDmReply: boolean
  replyIntervalMin: number
  dailyReplyLimit: number
  status: string
}

export interface MaterialLibrary {
  id?: number
  name: string
  type: string
  brandName: string
  language: string
  status: string
  materialCount: number
}

export interface Material {
  id?: number
  libraryId: number
  type: string
  imageUrl: string
  textContent: string
  tags: string[]
  status: string
  usedCount: number
  lastUsedAt?: string
}

export interface PostRecord {
  id: number
  pageId: string
  pageName: string
  materialId: number
  postContent: string
  postImageUrl: string
  status: string
  reviewMode: string
  scheduledAt: string
  publishedAt?: string
  failReason?: string
}

export interface MessageRecord {
  id: number
  platform: string
  messageType: string
  sourceId: string
  sourceName: string
  platformMessageId: string
  originalContent: string
  replyContent: string
  replyStatus: string
  replySource: string
  needsReview: boolean
}

export interface SensitiveWordRule {
  word: string
  matchType: string
  scope: string
  active: boolean
}
