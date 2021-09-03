<template>
  <div>
    <div class="container">

      <app-header></app-header>

      <transition name="slide" mode="out-in">
        <router-view></router-view>
      </transition>

    </div>
  </div>
</template>

<script>
import Login from '@/components/Login'

export default {
  name: 'App',
  components: {
    appHeader: Login
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
