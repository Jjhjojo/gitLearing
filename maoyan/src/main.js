import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store'

Vue.config.productionTip = false

import Http from '@/utils/http'
import './assets/reset.css'
import './assets/maoyan.css'
import 'animate.css'
Vue.prototype.$http=new Http()

//全局过滤器
import '@/filters/img'

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
