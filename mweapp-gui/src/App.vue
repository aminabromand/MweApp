<template>
  <div>
    <div class="container">

      <app-header></app-header>

      <img alt="Vue logo" src="./assets/mwe.png">

      <transition name="slide" mode="out-in">
        <router-view></router-view>
      </transition>

    </div>
  </div>
</template>

<script>
import Header from '@/components/Header'

export default {
  name: 'App',
  components: {
    appHeader: Header
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
