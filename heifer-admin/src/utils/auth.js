import Cookies from 'js-cookie'

const TokenKey = 'TokenKey'
const TokenTypeKey = 'tokenType'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function getTokenType() {
  return Cookies.get(TokenTypeKey)
}

export function setToken(token, tokenType = 'Bearer ') {
  Cookies.set(TokenTypeKey, tokenType)
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  Cookies.remove(TokenTypeKey)
  return Cookies.remove(TokenKey)
}
