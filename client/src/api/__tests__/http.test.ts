import { beforeEach, describe, expect, it, vi } from 'vitest'

const axiosMock = vi.hoisted(() => {
  const get = vi.fn()
  const post = vi.fn()
  const requestUse = vi.fn()

  return {
    get,
    post,
    requestUse,
    create: vi.fn(() => ({
      get,
      post,
      interceptors: {
        request: {
          use: requestUse,
        },
      },
    })),
  }
})

vi.mock('axios', () => ({
  default: {
    create: axiosMock.create,
    post: axiosMock.post,
  },
}))

import { getData } from '../http'

describe('http helpers', () => {
  beforeEach(() => {
    window.localStorage.clear()
    axiosMock.get.mockReset()
    axiosMock.post.mockReset()
  })

  it('does not login with hard-coded default credentials before data requests', async () => {
    axiosMock.get.mockResolvedValue({ data: { data: ['item'] } })

    const result = await getData('/api/items', [])

    expect(result).toEqual(['item'])
    expect(axiosMock.post).not.toHaveBeenCalledWith('/api/auth/login', expect.anything())
    expect(axiosMock.get).toHaveBeenCalledWith('/api/items')
  })

  it('reads auth tokens from the renamed project localStorage key', async () => {
    window.localStorage.setItem('auto-publish-posts-token', 'test-token')
    const applyRequestInterceptor = axiosMock.requestUse.mock.calls[0][0]
    const config = { headers: {} }

    const result = applyRequestInterceptor(config)

    expect(result.headers.Authorization).toBe('Bearer test-token')
  })
})
