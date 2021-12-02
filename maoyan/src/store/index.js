import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    city:{
      name:'北京',
      id:1,
    }
  },
  mutations: {
    setCity(state,city){
      state.city=city
    }
  },
  actions: {
    changeCity({commit},city){
      commit('setCity',city)
    }
  },
  modules: {
  }
})
