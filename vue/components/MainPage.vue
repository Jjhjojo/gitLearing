<template>
  <div class="container">
    <div class="header">
      <i class="fa fa-bookmark icon" aria-hidden="true"></i>
      <div class="search" @click="toSearch">
        <i class="fa fa-search"></i>
        <span>搜索</span>
      </div>
      <el-popover
        placement="bottom"
        width="50px"
        trigger="manual"
        content="分享"
        v-model="visible"
      >
        <i
          class="fa fa-plus-circle icon"
          slot="reference"
          @click="visible = !visible"
        ></i>
      </el-popover>
    </div>
    <ul class="tab-list">
      <li
        v-for="(tab, index) in tabs"
        :key="index"
        class="tab-item"
        :class="{ 'is-active': index === activeTabIndex }"
        @click="toggleTab(tab, index)"
      >
        {{ tab }}
      </li>
    </ul>
    <ul class="news-list">
      <li
        v-for="(item, index) in list"
        :key="index"
        class="news-item"
        @click="toNews(item)"
      >
        <div class="news-media">
          <img class="news-thumbnail" :src="item.thumbnail" />
          <div class="news-profile">
            <p>{{ item.title }}</p>
            <p class="news-mark">
              <Badge v-if="item.isFollow" text="关注"></Badge>
              <span>{{ item.source }}</span
              >&nbsp;&nbsp;
              <span>{{ item.time | supplyTime }}</span>
            </p>
          </div>
        </div>
      </li>
    </ul>
    <div class="footer">
      <div class="gotop" v-show="gotop" @click="toTop">Top</div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import Badge from "@/widgets/Badge";
export default {
  name: "MainPage",
  components: { Badge },
  data() {
    return {
      activeTabIndex: 0,
      visible: false,
      gotop: false,
    };
  },
  mounted() {
    this.$nextTick(function () {
      this.getCategories();
      this.getListByCategory({
        category: "推荐",
      });
    });
    window.addEventListener("scroll", this.handleScroll, true);
  },
  computed: {
    ...mapGetters("Category", ["categories"]),
    ...mapGetters("List", ["list"]),
    tabs() {
      return ["推荐", "关注"].concat(this.categories);
    },
  },
  methods: {
    ...mapActions("Category", ["getCategories"]),
    ...mapActions("List", ["getListByCategory"]),
    toSearch() {
      this.$router.push("/search");
    },
    toNews(news) {
      this.$router.push({ path: "news", query: { id: news.id } });
    },
    toggleTab(tab, index) {
      this.activeTabIndex = index;
      this.getListByCategory({
        category: tab,
      });
    },
    handleScroll() {
      let scrolltop =
        document.documentElement.scrollTop || document.body.scrollTop;
      scrolltop > 30 ? (this.gotop = true) : (this.gotop = false);
    },
    toTop() {
      let top = document.documentElement.scrollTop || document.body.scrollTop;
      // 实现滚动效果
      const timeTop = setInterval(() => {
        document.body.scrollTop =
          document.documentElement.scrollTop =
          top -=
            50;
        if (top <= 0) {
          clearInterval(timeTop);
        }
      }, 10);
    },
  },
  filters: {
    supplyTime(value) {
      return value.substring(0, 10);
    },
  },
};
</script>

<style scoped>
.container {
  height: 100%;
}
.header {
  padding: 15px;
  background: #fafafa;
  color: #393e46;
  overflow: hidden;
}
.search {
  display: inline-block;
  width: 70%;
  background: #e8f1f5;
  border-radius: 10px;
  padding: 5px;
  margin: auto;
}
.search > .fa-search {
  padding-right: 8px;
  padding-left: 8px;
}
.icon {
  vertical-align: middle;
  color: #005691;
  font-size: 25px;
}
.fa-bookmark {
  padding-right: 5%;
  margin-left: 3%;
}
.fa-plus-circle {
  padding-left: 5%;
}
.tab-list {
  list-style: none;
  border-bottom: 3px solid #e8e8e8;
  background: #fafafa;
}
.tab-item {
  box-sizing: border-box;
  display: inline-block;
  width: 14%;
  line-height: 32px;
  color: #222;
  font-size: 14px;
  text-align: center;
}
.tab-item.is-active {
  border-bottom: 2px solid #004a7c;
  font-weight: bold;
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
.gotop {
  text-align: center;
  position: fixed;
  right: 10%;
  bottom: 5%;
  cursor: pointer;
  padding: 10px;
  border-radius: 50%;
  background: #005691;
  color: white;
}
</style>
