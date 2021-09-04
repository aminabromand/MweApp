import { createStore } from 'vuex'
import { $axios } from './axios'

// Create a new store instance.
export const store = createStore({
  state: {
    loggedIn: false,
    count: 0,
    users: []
  },
  getters: {
    isLoggedIn (state) {
      return state.loggedIn
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
            $axios.defaults.headers.common.authorization = res.headers.authorization
          }
        )
        .catch(error => console.log(error))
    },
    logout () {
      delete $axios.defaults.headers.common.authorization
      this.state.loggedIn = false
    },
    loadUsers ({ commit }) {
      $axios.get('/api/user')
        .then(
          res => {
            commit('SET_USERS', res.data)
          }
        )
    }
  }
})
