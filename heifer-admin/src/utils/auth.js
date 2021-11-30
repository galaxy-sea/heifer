const TokenKey = 'TokenKey'
const TokenTypeKey = 'TokenType'
const TenantKey = 'TenantKey'

export function getToken() {
  return window.localStorage.getItem(TokenKey)
}

export function getTokenType() {
  return window.localStorage.getItem(TokenTypeKey)
}

export function setToken(token, tokenType = 'Bearer ') {
  window.localStorage.setItem(TokenTypeKey, tokenType)
  window.localStorage.setItem(TokenKey, token)
}

export function removeToken() {
  window.localStorage.removeItem(TokenTypeKey)
  window.localStorage.removeItem(TokenKey)
  window.localStorage.removeItem(TenantKey)
}

export function getTenant() {
  return window.localStorage.getItem(TenantKey)
}

export function setTenant(tenant) {
  window.localStorage.setItem(TenantKey, tenant)
}

export function removeTenant() {
  window.localStorage.removeItem(TenantKey)
}
