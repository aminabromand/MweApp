import { createApp } from 'vue'

import App from './App.vue'

import { createWebHashHistory, createRouter } from 'vue-router'
import { routes } from './routes.js'
import { store } from './store.js'

// Vue.config.productionTip = false;

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  console.log('checking route')
  console.log(to)
  console.log(from)
  if (to.matched.some(record => record.meta.withoutAuth)) {
    next() // does not require auth, make sure to always call next()!
  } else {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (!store.getters.isLoggedIn) {
      next({ name: 'Login' })
    } else {
      next() // go to wherever I'm going
    }
  }
})

// createApp(App).mount('#app')
createApp(App)
  .use(router)
  .use(store)
  .mount('#app')
