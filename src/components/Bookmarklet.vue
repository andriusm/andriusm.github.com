<template>
  <div class="bookmarklet">

    <md-card>
      <md-card-header>
        <div class="md-title">
          <div>
            <md-icon class="md-size-2x md-primary">class</md-icon>
            When done with the code drag this link onto the bookmark bar of the browser:
            <a v-bind:href="bookmarklet">{{bookmarkletName}}</a>
          </div>
        </div>
      </md-card-header>
    
      <md-card-content>
        <md-input-container>
          <label>Bookmarklet name</label>
          <md-input v-model="bookmarkletName"></md-input>
        </md-input-container>

        <md-input-container>
          <label>Bookmarklet code</label>
          <md-textarea v-model="bookmarkletCode"></md-textarea>
        </md-input-container>

        <md-button class="md-raised md-accent" v-on:click="clear()">Clear</md-button>
      </md-card-content>
    </md-card>

  </div>
</template>

<script>
export default {
  name: 'bookmarklet',
  data () {
    return {
      bookmarkletName: 'My bookmarklet',
      bookmarkletCode: 'alert("hello");'
    }
  },
  methods: {
    clear: function () {
      this.bookmarkletName = ''
      this.bookmarkletCode = ''
    }
  },
  computed: {
    bookmarklet: function () {
      return 'javascript:' + this.bookmarkletCode
    }
  }
}
</script>
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
