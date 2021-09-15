
<template>
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <router-link to="/gui" class="navbar-brand">MWE Web App</router-link>
      </div>

      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">

          <li><a v-on:click="onLogout">logout</a></li>

        </ul>
      </div>
    </div>
  </nav>

</template>

<script>
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  setup () {
    const store = useStore()
    const router = useRouter()
    return {
      store: store,
      router: router
    }
  },
  methods: {
    onLogout () {
      this.store.dispatch('logout')
        .then(() => {
          if (!this.store.getters.isLoggedIn) {
            this.router.replace('/gui/login')
          }
        })
    }
  }
}

</script>

<style scoped>

a {
  cursor: pointer;
}

</style>
