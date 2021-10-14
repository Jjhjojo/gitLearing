<template>
  <div class="container">
    <router-view v-if="isMobile"/>
    <h1 v-else>该页面需在手机端访问</h1>
  </div>
</template>

<script>
//import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'app',
  computed:{
    isMobile(){
      return document.body.clientWidth<500
    }
  }
}
</script>

<style scoped>
.container{
  height: 100%;
  user-select: none;
}
</style>
