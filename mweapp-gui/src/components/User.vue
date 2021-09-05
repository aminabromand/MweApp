<template>
  <div class="nearby-user">
    <div class="row">
      <div class="col-md-2 col-sm-2">
        <img v-bind:src="profilePicLink" alt="user"
             class="profile-photo-lg">
      </div>
      <div class="col-md-7 col-sm-7">
        <h5><a href="#" class="profile-link">{{ user.username }}</a></h5>
        <p>{{ user.occupation }}</p>
        <p class="text-muted">{{ user.description }}</p>
      </div>
      <div class="col-md-3 col-sm-3">
        <button v-if="isCsb" class="btn btn-light pull-right" @click="setSsbCount(user.id, user.ssbcount+1)">+</button>
        <button class="btn btn-primary pull-right">{{ user.ssbcount }}</button>
        <button v-if="isCsb" class="btn btn-light pull-right" @click="setSsbCount(user.id, user.ssbcount+1)">-</button>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex'

export default {
  name: 'User',
  props: ['user'],
  setup () {
    const store = useStore()
    return {
      store: store
    }
  },
  data () {
    return {
      profilePicLink: 'https://bootdey.com/img/Content/avatar/avatar' + (this.user.id % 8 + 1) + '.png'
    }
  },
  computed: {
    isCsb () {
      return this.checkIsCsb()
    }
  },
  methods: {
    checkIsCsb () {
      console.log('checkIsCsb')
      return this.store.getters.loggedInUser.roles.includes('ROLE_CSB')
    },
    setSsbCount (userid, ssbcount) {
      const responseOk = this.store.dispatch('setSsbCount', {
        userid: userid,
        ssbcount: ssbcount
      })
      if (responseOk) {
        new Promise(resolve => setTimeout(resolve, 100)).then(
          this.store.dispatch('loadUsers')
        )
      }
      console.log(responseOk)
    }
  }
}
</script>

<style scoped>
body {
  margin-top: 20px;
  background: #FAFAFA;
  font-weight: 400;
}

/*==================================================
Nearby People CSS
==================================================*/

.people-nearby .google-maps {
  background: #f8f8f8;
  border-radius: 4px;
  border: 1px solid #f1f2f2;
  padding: 20px;
  margin-bottom: 20px;
}

.people-nearby .google-maps .map {
  height: 300px;
  width: 100%;
  border: none;
}

.people-nearby .nearby-user {
  padding: 20px 0;
  border-top: 1px solid #f1f2f2;
  border-bottom: 1px solid #f1f2f2;
  margin-bottom: 20px;
}

img.profile-photo-lg {
  height: 80px;
  width: 80px;
  border-radius: 50%;
}

</style>
