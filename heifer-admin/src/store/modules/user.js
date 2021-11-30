import { login, logout, getInfo, getTenants } from '@/api/user'
import { getToken, setToken, removeToken, getTokenType, getTenant, setTenant } from '@/utils/auth'
import router, { resetRouter } from '@/router'

const state = {
  token: getToken(),
  tokenType: getTokenType(),
  tenants: [],
  tenant: getTenant(),
  name: '',
  avatar: '',
  introduction: '',
  roles: []
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_TOKEN_TYPE: (state, tokenType) => {
    state.tokenType = tokenType
  },
  SET_TENANT: (state, tenant) => {
    state.tenant = tenant
  },
  SET_TENANTS: (state, tenants) => {
    state.tenants = tenants
  },
  SET_INTRODUCTION: (state, introduction) => {
    state.introduction = introduction
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  }
}

const actions = {
  // user login
  login({ commit, state }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token)
        commit('SET_TOKEN_TYPE', data.tokenType)
        // todo weichangjin 改为登陆后选择
        // commit('SET_TENANT', 1)
        setToken(data.token, data.tokenType)
        // setTenantId(1)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  getTenant({ commit, state }) {
    return new Promise((resolve, reject) => {
      getTenants(state.token).then(response => {
        const { data } = response
        setToken(state.token, state.tokenType)
        commit('SET_TENANTS', data)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  setTenant({ commit, state }, tenant) {
    return new Promise(resolve => {
      commit('SET_TENANT', tenant)
      setTenant(tenant)
      resolve()
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response

        if (!data) {
          reject('Verification failed, please Login again.')
        }

        const { roles, name, avatar, introduction, tenant } = data

        // roles must be a non-empty array
        if (!roles || roles.length <= 0) {
          reject('getInfo: roles must be a non-null array!')
        }

        commit('SET_TENANT', tenant)
        commit('SET_ROLES', roles)
        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)
        commit('SET_INTRODUCTION', introduction)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_TENANT', '')
        commit('SET_TENANTS', [])
        commit('SET_ROLES', [])
        removeToken()
        resetRouter()

        // reset visited views and cached views
        // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
        dispatch('tagsView/delAllViews', null, { root: true })

        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  },

  // dynamically modify permissions
  async changeRoles({ commit, dispatch }, role) {
    const token = role + '-token'

    commit('SET_TOKEN', token)
    setToken(token)

    const { roles } = await dispatch('getInfo')

    resetRouter()

    // generate accessible routes map based on roles
    const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })
    // dynamically add accessible routes
    router.addRoutes(accessRoutes)

    // reset visited views and cached views
    dispatch('tagsView/delAllViews', null, { root: true })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
