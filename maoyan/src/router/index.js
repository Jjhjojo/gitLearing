import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/home/Home.vue'
import Movies from '@/views/home/movies/Movies.vue'
import CityPicker from '@/views/citypicker/CityPicker.vue'
import Theaters from '@/views/home/theaters/Theaters.vue'
import Profile from '@/views/home/profile/Profile.vue'
import Intheaters from '@/views/home/movies/InTheaters.vue'
import ComingSoon from '@/views/home/movies/ComingSoon.vue'

Vue.use(VueRouter)

const routes = [
  {
    path:'/',
    redirect:'/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    redirect:'/home/movies',
    children:[
      {
        path:'movies',
        name:'movies',
        component:Movies,
        redirect:'/home/movies/intheaters',
        children:[
          {
            path:'intheaters',
            name:'intheaters',
            component:Intheaters,
            meta:{
              index:1
            },
          },
          {
            path:'comingsoon',
            name:'comingsoon',
            component:ComingSoon,
            meta:{
              index:2
            },
          }
        ]
      },
      {
        path:'theaters',
        name:'theaters',
        component:Theaters
      },
      {
        path:'profile',
        name:'profile',
        component:Profile
      },
    ]
  },
  {
    path: '/citypicker',
    name: 'CityPicker',
    component: CityPicker,
  },
  {
    path: '/movies',
    name: 'Movies',
    component: Movies
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
