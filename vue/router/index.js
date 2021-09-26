import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'
import Index from '@/components/Index.vue'
import MainPage from '@/components/MainPage.vue'
import SearchPage from '@/components/SearchPage.vue'
import NewsPage from '@/components/NewsPage.vue'
import ResultPage from '@/components/ResultPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index
  },
  {
    path: '/main',
    name: 'MainpPage',
    component: MainPage
  },
  {
    path: '/search',
    name: 'SearchPage',
    component: SearchPage
  },
  {
    path: '/news',
    name: 'NewsPage',
    component: NewsPage
  },
  {
    path: '/result',
    name: 'ResultPage',
    component: ResultPage
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
