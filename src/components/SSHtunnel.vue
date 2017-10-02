<template>
  <div class="SSHtunnel">
    <md-card>
      <md-card-header>
        <md-layout md-gutter>
          <md-layout md-flex="5"> <md-icon class="md-size-2x md-primary">subway</md-icon> </md-layout>
          <md-layout md-flex="90">
            <div class="md-subhead2">I will assume that you already know what an SSH tunnel is if you're here and want to use it. This is a script that helps you with SSH tunnel setup. It generates a command line you would execute to create a local or a remote tunnel by asking you to specify some info about the servers. If you're on a Windows machine and don't have SSH client set up, you can download plink.</div>
          </md-layout>
        </md-layout>
      </md-card-header>
    
      <md-card-content>

      <md-layout md-gutter>
        <md-layout md-flex="10"><p style="">Create a</p></md-layout>
        <md-layout md-flex="15">
          <md-input-container>
            <md-select name="tunnel_type" v-model="tunnelType">
              <md-option value="local_tunnel">local tunnel</md-option>
              <md-option value="remote_tunnel">remote tunnel</md-option>
            </md-select>
          </md-input-container>
        </md-layout>
      </md-layout>

      <md-layout md-gutter v-if="tunnelType === 'local_tunnel'">
        <md-layout md-flex="10"><p>from local port</p></md-layout>
        <md-layout md-flex="5">
              <md-input-container>
                <md-input v-model="fromLocalPort"></md-input>
              </md-input-container>
        </md-layout>

        <md-layout md-flex="5"><p>to server</p></md-layout>
        <md-layout md-flex="10">
              <md-input-container>
                <md-input v-model="toServer"></md-input>
              </md-input-container>
        </md-layout>

        <md-layout md-flex="5"><p>port</p></md-layout>
        <md-layout md-flex="5">
              <md-input-container>
                <md-input v-model="toPort"></md-input>
              </md-input-container>
        </md-layout>

        <md-layout md-flex="10"><p>via server</p></md-layout>
        <md-layout md-flex="10">
              <md-input-container>
                <md-input v-model="viaServer"></md-input>
              </md-input-container>
        </md-layout>

        <md-layout md-flex="10"><p>auth with user</p></md-layout>
        <md-layout md-flex="10">
              <md-input-container>
                <md-input v-model="localAuthUser"></md-input>
              </md-input-container>
        </md-layout>
      <md-layout></md-layout>
      </md-layout>





      <md-layout md-gutter v-else>
        <md-layout md-flex="10"><p>from remote port</p></md-layout>
        <md-layout md-flex="5">
              <md-input-container>
                <md-input v-model="fromRemotePort"></md-input>
              </md-input-container>
        </md-layout>

        <md-layout md-flex="5"><p>on server</p></md-layout>
        <md-layout md-flex="10">
              <md-input-container>
                <md-input v-model="remoteHost"></md-input>
              </md-input-container>
        </md-layout>

        <md-layout md-flex="5"><p>to port</p></md-layout>
        <md-layout md-flex="5">
              <md-input-container>
                <md-input v-model="toRemotePort"></md-input>
              </md-input-container>
        </md-layout>

        <md-layout md-flex="5"><p>on server</p></md-layout>
        <md-layout md-flex="10">
              <md-input-container>
                <md-input v-model="onRemoteServer"></md-input>
              </md-input-container>
        </md-layout>

        <md-layout md-flex="15"><p>authenticate with user</p></md-layout>
        <md-layout md-flex="10">
              <md-input-container>
                <md-input v-model="remoteAuthUser"></md-input>
              </md-input-container>
        </md-layout>
      </md-layout>

      <md-layout class="result">
        <pre>
{{ SSHline }}
        </pre>
      </md-layout>

      </md-card-content>
    </md-card>
  </div>
</template>

<script>
export default {
  name: 'SSHtunnel',
  data () {
    return {
      tunnelType: 'local_tunnel',

      fromLocalPort: '',
      toServer: '',
      toPort: '',
      viaServer: '',
      localAuthUser: '',

      fromRemotePort: '',
      remoteHost: '',
      toRemotePort: '',
      onRemoteServer: '',
      remoteAuthUser: ''
    }
  },
  computed: {
    SSHline: function () {
      if (this.tunnelType === 'local_tunnel') {
        if (!this.fromLocalPort || !this.toServer || !this.toPort || !this.viaServer || !this.localAuthUser) return ''
        return 'ssh -N -L ' + this.fromLocalPort + ':' + this.toServer + ':' + this.toPort + ' ' + this.localAuthUser + '@' + this.viaServer
      } else {
        if (!this.fromRemotePort || !this.remoteHost || !this.toRemotePort || !this.onRemoteServer || !this.remoteAuthUser) return ''
        return 'ssh -N -R ' + this.fromRemotePort + ':' + this.onRemoteServer + ':' + this.toRemotePort + ' ' + this.remoteAuthUser + '@' + this.remoteHost
      }
    }
  }
}
</script>

<style scoped>
.md-layout p {
  margin: 0 0px;
  width: 100%;
  text-align: center;
  line-height: 76px;
}
.result {
  background: #f0f0f0;
  height: 5em;
}
</style>
