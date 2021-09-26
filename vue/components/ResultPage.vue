<template>
  <div class="container">
    <div class="header">
      <i class="fa fa-angle-left icon-return" @click="toMain"></i>
      <div class="search-box" @click="toSearch">
        <i class="fa fa-search"></i>
        <span>{{keyword}}</span>
      </div>
    </div>
    <ul class="news-list" v-if="list.length">
      <li v-for="(item,index) in list"
      :key="index"
      class="news-item"
      @click="toNews(item)">
      <div class="news-media">
        <img class="news-thumbnail" :src="item.thumbnail" >
        <div class="news-profile">
          <p>{{item.title}}</p>
          <p class="news-mark">
            <Badge v-if="item.isFollow" text="关注"></Badge>
            <span>{{item.source}}</span>&nbsp;&nbsp;
            <span>{{item.time|supplyTime}}</span>
          </p>
        </div>
      </div>
      </li>
    </ul>
    <p class="empty" v-else>抱歉，没有您搜索的内容，请重新输入！</p>
  </div>
</template>

<script>
import {mapGetters,mapActions} from 'vuex'
import Badge from '@/widgets/Badge'
export default{
  name:'MainPage',
  components:{Badge},
  data(){
    return{
      keyword:''
    }
  },
  mounted(){
    this.$nextTick(function(){
      this.keyword=this.$route.query.keyword
      if(!this.keyword){
        this.$router.push('/main')
      }
      this.getListByKeyword({
        keyword:this.keyword
      })
    })
  },
  computed:{
    ...mapGetters('List',['list'])
  },
  methods:{
    ...mapActions('List',['getListByKeyword']),
    toMain(){
      this.$router.push('/main')
    },
    toSearch(){
      this.$router.push('/search')
    },
    toNews(news){
      this.$router.push({path:'news',query:{id:news.id}})
    }
  },
  filters:{
    supplyTime(value){
      return value.substring(0,10)
    }
  }
}
</script>

<style scoped>
.container {
    height: 100%;
  }
  .header {
  padding: 13px 15px;
  background-color: #fafafa;
  overflow: hidden;
}
.icon-return {
  text-align: center;
  vertical-align: middle;
  color: #004a7c;
  font-size: 24px;
  line-height: 32px;
  margin-right: 20px;
}
.search-box {
  display: inline-block;
  vertical-align: middle;
  width: calc(100% - 70px);
  line-height: 32px;
  background-color: #e8f1f5;
  border-radius: 15px;
  padding-left: 10px;
  padding-right: 10px;
  border: 1px solid #112d4e;
}
.search-box > i {
  color: #707070;
}
.fa-search {
  margin-right: 10px;
}
.news-list {
  list-style: none;
  height: calc(100% - 80px);
  overflow-y: auto;
}
.news-item {
  overflow: hidden;
  padding: 12px 8px;
  border-bottom: 5px solid #e8f1f5;
}
.news-media {
  overflow: hidden;
  position: relative;
}
.news-thumbnail {
  float: right;
  width: 100px;
  padding-left: 5px;
}
.news-profile {
  overflow: hidden;
}
.news-mark {
  margin-top: 5px;
  font-size: 12px;
  color: #a9a9a9;
}
  .empty-info {
    margin-top: 5px;
    margin-left: 8px;
  }
</style>