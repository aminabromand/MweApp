import { createStore } from 'vuex'
import { $axios } from './axios'

// Create a new store instance.
export const store = createStore({
  state: {
    loggedIn: false,
    count: 0
  },
  mutations: {
    increment (state) {
      state.count++
    }
  },
  actions: {
    increment (context) {
      $axios.post('/login', { username: 'aminator', password: 'abcdef9h' })
        .then(res => console.log(res))
        .catch(error => console.log(error))

      context.commit('increment')
    }
  }
})
