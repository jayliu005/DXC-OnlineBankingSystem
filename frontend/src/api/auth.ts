import type { AuthUser, LoginRequest, RegisterRequest, UsernameAvailability } from '@/types/auth'

interface ApiErrorPayload {
  message?: string
  fieldErrors?: Record<string, string>
}

export class ApiError extends Error {
  fieldErrors: Record<string, string>

  constructor(message: string, fieldErrors: Record<string, string> = {}) {
    super(message)
    this.name = 'ApiError'
    this.fieldErrors = fieldErrors
  }
}

async function request<T>(path: string, init?: RequestInit): Promise<T> {
  const response = await fetch(path, {
    credentials: 'include',
    ...init,
    headers: {
      'Content-Type': 'application/json',
      ...init?.headers,
    },
  })

  if (!response.ok) {
    const payload = (await response.json().catch(() => ({}))) as ApiErrorPayload
    throw new ApiError(payload.message ?? 'The request could not be completed', payload.fieldErrors)
  }

  if (response.status === 204) {
    return undefined as T
  }

  return response.json() as Promise<T>
}

export function login(payload: LoginRequest) {
  return request<AuthUser>('/api/auth/login', {
    method: 'POST',
    body: JSON.stringify(payload),
  })
}

export function register(payload: RegisterRequest) {
  return request<AuthUser>('/api/auth/register', {
    method: 'POST',
    body: JSON.stringify(payload),
  })
}

export function logout() {
  return request<void>('/api/auth/logout', { method: 'POST' })
}

export function getSession() {
  return request<AuthUser>('/api/auth/session')
}

export function checkUsernameAvailability(userName: string) {
  const query = new URLSearchParams({ userName })
  return request<UsernameAvailability>(`/api/auth/username-availability?${query}`)
}
