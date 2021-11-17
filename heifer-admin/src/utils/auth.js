const TokenKey = 'TokenKey'
const TokenTypeKey = 'tokenType'

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
}
