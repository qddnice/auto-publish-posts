import { describe, expect, it } from 'vitest'
import { statusText, statusType } from '../status'

describe('status helpers', () => {
  it('maps backend status codes to Chinese labels and Element Plus tag types', () => {
    expect(statusText('REPLIED')).toBe('已回复')
    expect(statusType('REPLIED')).toBe('success')
    expect(statusText('MANUAL_REVIEW')).toBe('待人工')
    expect(statusType('FAILED')).toBe('danger')
  })
})
