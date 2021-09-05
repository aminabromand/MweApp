<template>
      <transition name="slide" mode="out-in">
        <router-view></router-view>
      </transition>

</template>

<script>
import { useRouter } from 'vue-router'

export default {
  name: 'App',
  setup () {
    const router = useRouter()
    return {
      router: router
    }
  },
  created () {
    this.router.replace('/login')
  }
}
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
