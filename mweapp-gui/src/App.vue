<template>
  <div>
    <div class="container">

      <app-body></app-body>

      <transition name="slide" mode="out-in">
        <router-view></router-view>
      </transition>

    </div>
  </div>
</template>

<script>
import BlankPage from '@/components/BlankPage'
import { useRouter } from 'vue-router'

export default {
  name: 'App',
  setup () {
    const router = useRouter()
    return {
      router: router
    }
  },
  components: {
    appBody: BlankPage
  },
  created () {
    this.router.replace('/login')
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
