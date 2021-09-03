<template>

          <div class="nearby-user">
            <div class="row">
              <div class="col-md-2 col-sm-2">
                <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="user"
                     class="profile-photo-lg">
              </div>
              <div class="col-md-7 col-sm-7">
                <h5><a href="#" class="profile-link">{{ user.name }}</a></h5>
                <p>{{ user.description }}</p>
                <p class="text-muted">{{ user.phone }}</p>
                <p class="text-muted">{{ user.email }}</p>
                <p class="text-muted">{{ user.address }}</p>
              </div>
              <div class="col-md-3 col-sm-3">
                <button class="btn btn-primary pull-right">+</button>
                <button class="btn btn-primary pull-right">{{ user.ssbcount }}</button>
                <button class="btn btn-primary pull-right">-</button>
              </div>
            </div>
          </div>

</template>

<script>
export default {
  name: 'User',
  props: ['user']
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
