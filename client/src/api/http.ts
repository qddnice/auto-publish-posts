import axios from 'axios'
import type { ApiResponse } from './types'

export const http = axios.create({
  baseURL: '',
  timeout: 15000,
})

http.interceptors.request.use((config) => {
  const token = window.localStorage.getItem('auto-publish-posts-token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

export async function getData<T>(url: string, fallback: T): Promise<T> {
  try {
    const response = await http.get<ApiResponse<T>>(url)
    return response.data.data
  } catch {
    return fallback
  }
}

export async function postData<T>(url: string, payload: unknown, fallback: T): Promise<T> {
  try {
    const response = await http.post<ApiResponse<T>>(url, payload)
    return response.data.data
  } catch {
    return fallback
  }
}
