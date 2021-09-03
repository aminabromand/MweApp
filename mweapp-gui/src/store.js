import { createStore } from 'vuex'
import { $axios } from './axios'

// Create a new store instance.
export const store = createStore({
  state: {
    loggedIn: false,
    count: 0
  },
  getters: {
    isLoggedIn (state) {
      return state.loggedIn
    }
  },
  mutations: {
    increment (state) {
      state.count++
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
          }
        )
        .catch(error => console.log(error))
    }
  }
})
