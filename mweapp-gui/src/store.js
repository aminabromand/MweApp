import { createStore } from 'vuex'
import { $axios } from './axios'

// Create a new store instance.
export const store = createStore({
  state: {
    loggedIn: false,
    loggedInUser: {},
    count: 0,
    users: []
  },
  getters: {
    isLoggedIn (state) {
      return state.loggedIn
    },
    loggedInUser (state) {
      return state.loggedInUser
    },
    users (state) {
      return state.users
    }
  },
  mutations: {
    increment (state) {
      state.count++
    },
    'SET_USERS' (state, users) {
      state.users = users
    },
    'SET_LOGGEDIN_USER' (state, user) {
      state.loggedInUser = user
    }
  },
  actions: {
    increment (context) {
      $axios.post('/api/login', { username: 'aminator', password: 'abcdef9h' })
        .then(res => console.log(res))
        .catch(error => console.log(error))
      context.commit('increment')
    },
    login ({ commit, dispatch }, authData) {
      return $axios.post(
        '/api/login',
        { username: authData.username, password: authData.password })
        .then(
          res => {
            console.log(res.headers.authorization)
            this.state.loggedIn = true
            commit('SET_LOGGEDIN_USER', res.data)
            $axios.defaults.headers.common.authorization = res.headers.authorization
          }
        )
        .catch(error => console.log(error))
    },
    logout () {
      delete $axios.defaults.headers.common.authorization
      this.state.loggedIn = false
      this.state.loggedInUser = {}
    },
    setSsbCount ({ commit, dispatch }, payload) {
      const response = $axios.patch(
        '/api/user/' + payload.userid,
        { ssbcount: payload.ssbcount }
      ).then(res => {
        console.log(res.data)
        dispatch('loadUsers')
      })
      return response && response.status === 200
    },
    loadUsers ({ commit }) {
      $axios.get('/api/user')
        .then(res => {
          commit('SET_USERS', res.data)
        })
    },
    resetpassword ({ commit }, authData) {
      console.log(authData)
      return $axios.post(
        '/api/user/requestpasswordreset',
        { email: authData.email })
        .catch(error => console.log(error))
    }
  }
})
