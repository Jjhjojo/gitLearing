<template>
  <div class="container">
    <div class="header">
      <i class="fa fa-angle-left icon-header" @click="toMain"></i>
      <div class="pull-right">
        <i class="fa fa-search icon-header" @click="toSearch"></i>
        <i class="fa fa-ellipsis-v icon-header"></i>
      </div>
    </div>
    <div class="text-nav">
      <ly-tab
        v-model="selectedId"
        :items="items"
        :options="options"
        :change="selectChange()"
        class="sliding"
      >
      </ly-tab>
    </div>
    <div class="content">
      <h2>{{ news.title }}</h2>
      <p class="news-profile">{{ news.source }}<br />{{ news.time }}</p>
      <div v-html="news.content"></div>
      <ul>
        <li class="keyword-item"><i class="fa fa-key"></i></li>
        <li
          v-for="(keyword, index) in news.keywords"
          :key="index"
          class="keyword-item"
        >
          {{ keyword }}
        </li>
      </ul>
      {{ date }}
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  name: "NewsPage",
  data() {
    return {
      selectedId: 0,
      items: [
        { label: "中文" },
        { label: "繁体" },
        { label: "English" },
        { label: "Español" },
        { label: "Français" },
        { label: "日本語" },
        { label: " Дания " },
      ],
      options: {
        activeColor: "#004a7c",
      },
      date: new Date(),
    };
  },
  mounted() {
    this.$nextTick(function () {
      this.getNews({
        id: this.$route.query.id,
      });
    });
    let _this = this; // 声明一个变量指向Vue实例this，保证作用域一致
    this.timer = setInterval(() => {
      _this.date = new Date(); // 修改数据date
    }, 1000);
  },
  beforeDestroy() {
    if (this.timer) {
      clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
    }
  },
  computed: {
    ...mapGetters("News", ["news"]),
  },
  methods: {
    ...mapActions("News", ["getNews"]),
    toMain() {
      this.$router.push("/main");
    },
    toSearch() {
      this.$router.push("/search");
    },
    selectChange() {
      console.log(this.selectedId);
    },
  },
};
</script>

<style scoped>
.container {
  height: 100%;
}
.header {
  padding: 10px;
  background: #fafafa;
  color: #393e46;
}
.icon-header {
  padding: 10px;
  vertical-align: middle;
  font-size: 20px;
}
.content {
  box-sizing: border-box;
  padding: 25px 15px;
  height: calc(100% - 35px);
  overflow-y: auto;
  color: #222;
}
.news-profile {
  padding-top: 10px;
  padding-bottom: 10px;
  color: #999;
  border-bottom: 1px dashed #004a7c;
}
.keyword-item {
  margin-right: 10px;
  list-style: none;
  display: inline-block;
  color: #004a7c;
}
.keyword-item:first-child {
  font-size: 16px;
  margin-right: 5px;
  padding-bottom: 10px;
}
</style>