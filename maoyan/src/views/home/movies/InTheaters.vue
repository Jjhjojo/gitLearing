<template>
  <div class="movie-list">
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
    },
  },
};
</script>
<style lang="stylus" scoped>

</style>