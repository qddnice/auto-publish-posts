import { getData, postData } from './http'
import type {
  FbConfig,
  Material,
  MaterialLibrary,
  MessageRecord,
  PostRecord,
  SensitiveWordRule,
  TtConfig,
} from './types'

export const fallbackFbConfigs: FbConfig[] = [
]

export const fallbackTtConfigs: TtConfig[] = [
]

export const fallbackMessages: MessageRecord[] = [
]

export const fallbackLibraries: MaterialLibrary[] = [
]

export const fallbackMaterials: Material[] = [
]

export const fallbackPosts: PostRecord[] = [
]

const fallbackApprovedPost: PostRecord = {
  id: 0,
  pageId: '',
  pageName: '',
  materialId: 0,
  postContent: '',
  postImageUrl: '',
  status: 'APPROVED',
  reviewMode: 'MANUAL',
  scheduledAt: '',
}

export function fetchFbConfigs() {
  return getData('/api/config/fb/list', fallbackFbConfigs)
}

export function fetchTtConfigs() {
  return getData('/api/config/tt/list', fallbackTtConfigs)
}

export function fetchMessages() {
  return getData('/api/reply/workbench', fallbackMessages)
}

export function fetchLibraries() {
  return getData('/api/material/library/list', fallbackLibraries)
}

export function fetchMaterials() {
  return getData('/api/material/list', fallbackMaterials)
}

export function fetchPosts() {
  return getData('/api/post/records', fallbackPosts)
}

export function fetchSensitiveWords() {
  return getData<SensitiveWordRule[]>('/api/sensitive-word/list', [])
}

export function approvePost(id: number) {
  return postData<PostRecord>(`/api/post/approve`, { id }, { ...fallbackApprovedPost, id })
}
