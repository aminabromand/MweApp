<template>

  <app-header></app-header>

  <div class="container">
    <div class="row">
      <div class="col-md-8">
        <div class="people-nearby">

          <app-user v-for="userdata in users" :user="userdata" :key="userdata.id"></app-user>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header'
import User from '@/components/User'

export default {
  name: 'UserList',
  components: {
    appHeader: Header,
    appUser: User
  },
  data () {
    return {
      users: [
        {
          id: 1,
          name: 'aminator',
          description: 'Software Engineer',
          phone: '0176 23 56 3555',
          email: 'amin@abromand.com',
          address: 'Vogelrohrsheide 124a',
          ssbcount: 3
        },
        {
          id: 2,
          name: 'fame',
          description: 'Sales Engineer',
          phone: '0176 23 56 3555',
          email: 'felixblanke@gmail.com',
          address: 'Muenchen',
          ssbcount: 7
        }
      ]
    }
  }
}
</script>

<style scoped>
body {
  margin-top: 20px;
  background: #FAFAFA;
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
