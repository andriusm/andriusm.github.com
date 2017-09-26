<template>
  <div id="app">
    <bookmarklet></bookmarklet>
  </div>
</template>

<script>
import Bookmarklet from './components/Bookmarklet'

export default {
  name: 'app',
  components: {
    Bookmarklet
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  margin: 40px;
}
</style>
