import { getLanguage } from '@/lang/index'

const state = {
  sidebar: {
    opened: window.localStorage.getItem('sidebarStatus') ? !!+window.localStorage.getItem('sidebarStatus') : true,
    withoutAnimation: false
  },
  device: 'desktop',
  language: getLanguage(),
  size: window.localStorage.getItem('size') || 'medium'
}

const mutations = {
  TOGGLE_SIDEBAR: state => {
    state.sidebar.opened = !state.sidebar.opened
    state.sidebar.withoutAnimation = false
    if (state.sidebar.opened) {
      window.localStorage.setItem('sidebarStatus', 1)
    } else {
      window.localStorage.setItem('sidebarStatus', 0)
    }
  },
  CLOSE_SIDEBAR: (state, withoutAnimation) => {
    window.localStorage.setItem('sidebarStatus', 0)
    state.sidebar.opened = false
    state.sidebar.withoutAnimation = withoutAnimation
  },
  TOGGLE_DEVICE: (state, device) => {
    state.device = device
  },
  SET_LANGUAGE: (state, language) => {
    state.language = language
    window.localStorage.setItem('language', language)
  },
  SET_SIZE: (state, size) => {
    state.size = size
    window.localStorage.setItem('size', size)
  }
}

const actions = {
  toggleSideBar({ commit }) {
    commit('TOGGLE_SIDEBAR')
  },
  closeSideBar({ commit }, { withoutAnimation }) {
    commit('CLOSE_SIDEBAR', withoutAnimation)
  },
  toggleDevice({ commit }, device) {
    commit('TOGGLE_DEVICE', device)
  },
  setLanguage({ commit }, language) {
    commit('SET_LANGUAGE', language)
  },
  setSize({ commit }, size) {
    commit('SET_SIZE', size)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
