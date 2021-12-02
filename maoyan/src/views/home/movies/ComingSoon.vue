<template>
  <div class="movie-list">
    <div v-html="mostExpected"></div>
    <MovieList :movie-list="movieList"></MovieList>
  </div>
</template>
<script>
import MovieList from '@/components/MovieList.vue'
export default {
  components: { MovieList },
  props: {
    movieList: {
      type: Array,
    }
  },
  data(){
    return{
      mostExpected:''
    }
  },
  async mounted(){
    let result=await this.$http.get({
      url:'/ajax/topRatedMovies'
    })
    this.mostExpected=result
    // console.log(result)
  }
};
</script>
<style lang="stylus" scoped>

</style>