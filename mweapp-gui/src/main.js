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

// createApp(App).mount('#app')
createApp(App)
  .use(router)
  .use(store)
  .mount('#app')
