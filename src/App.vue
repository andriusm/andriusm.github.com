<template>
  <div id="app">
    <md-toolbar>
      <h2 class="md-title" style="flex: 1">Experiments 'n stuff</h2>
    </md-toolbar>

    <md-tabs>
      <md-tab id="intro" md-label="Intro">
        <p>These are some of the experiments, tools and hacks that I thre together.</p>
      </md-tab>

      <md-tab id="bookmarklet" md-label="Bookmarklet">
        <bookmarklet></bookmarklet>
      </md-tab>
    </md-tabs>
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
}
</style>
