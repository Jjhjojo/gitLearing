<template>
  <div class="movie-container">
    <main>
      <nav class="movie-nav">
        <ul>
          <li  @click="handleCityClick">
            {{city.name}}
            <span class="yo-ico">&#xf033;</span>
          </li>
          <li>
            <router-link to="/home/movies/intheaters" tag="span" active-class="active">热映</router-link>
            <router-link to="/home/movies/comingsoon" tag="span" active-class="active">待映</router-link>
          </li>
          <li>
            <b class="movie-ico">&#xe62f;</b>
          </li>
        </ul>
      </nav>
      <section class="movie-list">
        <van-skeleton
          avatar-shape
          avatar
          :row="3" 
          :loading="showSkeleton"
          avatar-size="60"
          class="skeleton"
          v-for="i in 7"
          :key="'a'+i"
        >
        </van-skeleton>
        <van-pull-refresh v-model="refreshing" @refresh="onRefresh">
          <van-list
            v-model="loading"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
            :immediate-check="false"
          >
             <transition>
              <router-view :movie-list="movieList"></router-view>
            </transition>
          </van-list>
        </van-pull-refresh>
      </section>
    </main>
  </div>
</template>
<script>
// import axios from 'axios'
import Vue from 'vue';
import { List , PullRefresh ,Skeleton } from 'vant';
import {mapState} from 'vuex'

Vue.use(List).use(PullRefresh);
Vue.use(Skeleton);
export default {
  data() {
    return{
      movieList:[],
      loading: false,
      finished: false,
      refreshing: false,
      showSkeleton:true,
      url: '/mmdb/movie/v2/list/hot.json'
    }
  },
  created(){
    this.hasMore=false
    this.limit=10
    this.ct='北京'
    this.offset=0
  },
  async mounted(){
    await this._loadData()
    this.showSkeleton=false
    // console.log(result)
  },
  computed:{
    ...mapState(['city'])
  },
  methods:{
    async _loadData(){
      let result=await this.$http.get({
        url:this.url,
        params:{
            limit:this.limit,
            offset:this.offset,
            ct:this.city.name,
            ci:this.city.id
        }
      })
      let {hot,coming,paging:{hasMore}}=result.data
      this.movieList=[
        ...this.movieList,
        ...hot,
        ...coming
      ]
      this.hasMore=hasMore
    },
    handleCityClick(){
      this.$router.push('/citypicker')
    },
    async onLoad() {
      if (this.refreshing) {
          this.movieList = []
          this.refreshing = false
          // console.log(this.refreshing)
        }

      await this._loadData()
      this.loading = false

      console.log(this.hasMore)

      if (!this.hasMore) {
        this.finished = true
      }

      this.offset+=this.limit
    },
    onRefresh() {
      //清空列表数据
      this.offset=0
      this.finished=false

      //重新加载数据
      //loading为ture代表加载状态
      this.loading=true
      this.onLoad()
    },
  },
    watch:{
      $route:{
        handler(route){
          if(route.name==='intheaters'){
            this.url = '/mmdb/movie/v2/list/hot.json'
          }else{
            this.url = '/mmdb/movie/v1/list/wish/order/coming.json'
          }
          this.refreshing = true
          this.onRefresh()
        },
        immediate:true
      }
  }
}
</script>

<style lang="stylus" scoped>
@import "~@/assets/stylus/icon.styl"
.movie-container
  flex 1
  overflow hidden
  main
    position absolute
    width 100%
    height 100% 
    flex 1
    display flex
    flex-direction column
    .movie-nav
      height .44rem
      > ul
        border1px(0 0 1px 0)
        display flex
        li:first-child
          flex 100
          display flex
          justify-content flex-start
          align-items center
          color #666
          padding-left .15rem
        li:nth-child(2)
          flex 231
          display flex
          justify-content center
          align-items center
          span
            display inline-block
            height .44rem
            width .6rem
            padding 0 .1rem
            line-height .44rem
            color #666
            &:first-child
              margin-right .3rem
              margin-left -.4rem
            &.active
              border-bottom 2px solid #cd4c42
              color #cd4c42

        li:last-child
          width .44rem
          border1px(0 0 0 1px)
          font-size .2rem
          line-height .44rem
          text-align center
          color #cd4c42
    .movie-list
      // position relative
      flex 1
      overflow-y scroll
      .skeleton
        margin-top .2rem
      // .van-pull-refresh
      //   position absolute
      //   top 0
      //   width 100%
</style>