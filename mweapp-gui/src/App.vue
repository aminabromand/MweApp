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
    console.log('hello')
    console.log(this.$route)
    console.log(this.$route.name)
    console.log(this.$route.params.baseaccount)
    console.log(this.router.currentRoute.value.name)
    console.log(window.location.pathname)
    console.log('hello2')
    // if (this.router.currentRoute.value === '/gui/settokenpassword') return
    // this.router.replace('/gui/login')
  },
  beforeMount () {
    console.log('beforeMount')
    console.log(this.$route)
    console.log(this.$route.name)
    console.log(this.$route.params.baseaccount)
    console.log(this.router.currentRoute.value.name)
    console.log(window.location.pathname)
    console.log('hello2')
    // if (this.router.currentRoute.value === '/gui/settokenpassword') return
    // this.router.replace('/gui/login')
  },
  mounted () {
    console.log('mounted')
    console.log(this.$route)
    console.log(this.$route.name)
    console.log(this.$route.params.baseaccount)
    console.log(this.router.currentRoute.value.name)
    console.log(window.location.pathname)
    console.log('hello2')
    // if (this.router.currentRoute.value === '/gui/settokenpassword') return
    // this.router.replace('/gui/login')
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
