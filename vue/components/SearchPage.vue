<template>
  <div class="container">
    <div class="header">
      <i class="fa fa-angle-left icon-return" @click="toMain"></i>
      <div class="search-box">
        <i class="fa fa-search"></i>
        <label for="search" v-show="false"></label>
        <input
          id="search"
          type="text"
          placeholder="请输入关键字"
          class="ipt-search"
          v-model="keyword"
        />
      </div>
      <span class="btn-search" @click="toResult">搜&nbsp;索</span>
    </div>
    <div class="content">
      <div class="search-banner" v-if="recordKeywords.length">
        <div class="banner-title">
          <span>历史记录</span>
          <i class="fa fa-trash btn-banner" @click="removeRecord"></i>
        </div>
        <ul class="keyword-list">
          <li
            v-for="(keyword, index) in recordKeywords"
            :key="index"
            class="keyword-item one-line"
            @click="toResultByRecord(keyword)"
          >
            {{ keyword }}
          </li>
        </ul>
      </div>
      <div class="search-banner">
        <div class="banner-title">
          <span>搜索发现</span>
          <i
            class="fa btn-banner"
            :class="{ 'fa-eye': isVisible, 'fa-eye-slash': !isVisible }"
            @click="toggleVisible"
          ></i>
        </div>
        <ul class="keyword-list" v-if="isVisible">
          <li
            v-for="(keyword, index) in recKeywords"
            :key="index"
            class="keyword-item one-line"
            @click="toResultByRecord(keyword)"
          >
            {{ keyword }}
          </li>
        </ul>
      </div>
    </div>
    <div class="pic">
      <h3>您可能感兴趣</h3>
      <el-carousel indicator-position="outside" :height="bannerHeight + 'px'">
        <div>
          <el-carousel-item v-for="(file, index) in object" :key="index">
            <img :src="file.src" />
            <span
              style="
                position: absolute;
                bottom: 0.2rem;
                left: 0.15rem;
                background: gray;
                color: white;
                padding: 10px;
              "
              >{{ file.title }}</span
            >
          </el-carousel-item>
        </div>
      </el-carousel>
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchPage",
  data() {
    return {
      keyword: "",
      recordKeywords: [],
      recKeywords: [
        "中国",
        "土耳其",
        "宋涛",
        "NBA",
        "爵士",
        "童瑶",
        "白玉兰奖",
        "机器人",
        "党外交",
      ],
      isVisible: true,
      object: [
        {
          title: "高天厚土铺展大美画卷——习近平总书记考察青海纪实",
          src: "http://www.chinatoday.com.cn/zw2018/twxw/202106/W020210611419325841972.jpg",
        },
        {
          title: "一“槽”飞架，让远水解近渴",
          src: "http://www.chinatoday.com.cn/zw2018/twxw/202105/W020210524259170242840.jpg",
        },
        {
          title: "井冈山上的“红色小百灵”",
          src: "http://www.chinatoday.com.cn/zw2018/twxw/202106/W020210601264420925976.jpg",
        },
        {
          title: "白鹤滩水电站大坝全线浇筑到顶",
          src: "http://www.chinatoday.com.cn/zw2018/twxw/202106/W020210601263494869529.jpg",
        },
      ],
      bannerHeight: 1000,
      screenWidth: 0,
    };
  },
  mounted() {
    this.$nextTick(function () {
      this.getRecord();
    });
    // 首次加载时,需要调用一次
    this.screenWidth = window.innerWidth;
    this.setSize();
    // 窗口大小发生改变时,调用一次
    window.onresize = () => {
      this.screenWidth = window.innerWidth;
      this.setSize();
    };
  },
  methods: {
    getRecord() {
      let record = window.localStorage.getItem("ztRecord");
      if (record) {
        this.recordKeywords = JSON.parse(record).keywords;
      }
    },
    setRecord() {
      window.localStorage.setItem(
        "ztRecord",
        JSON.stringify({
          keywords: this.recordKeywords,
        })
      );
    },
    removeRecord() {
      this.recordKeywords = [];
      window.localStorage.removeItem("ztRecord");
    },
    toggleVisible() {
      this.isVisible = !this.isVisible;
    },
    toMain() {
      this.$router.push("/main");
    },
    toResult() {
      if (this.keyword) {
        if (
          this.recordKeywords.findIndex((item) => item === this.keyword) === -1
        ) {
          this.recordKeywords.push(this.keyword);
          this.setRecord();
        }
        this.$router.push({
          path: "/result",
          query: { keyword: this.keyword },
        });
      }
    },
    toResultByRecord(keyword) {
      this.$router.push({ path: "/result", query: { keyword } });
    },
    setSize: function () {
      // 通过浏览器宽度(图片宽度)计算高度
      this.bannerHeight = (400 / 800) * this.screenWidth;
    },
  },
};
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
  width: calc(100% - 100px);
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
.ipt-search {
  width: calc(100% - 26px);
  border: none;
  outline: none;
  line-height: 32px;
  background: #e8f1f5;
}
.btn-search {
  display: inline-block;
  vertical-align: middle;
  line-height: 32px;
  padding-left: 15px;
  color: #004a7c;
  font-size: 14px;
  font-weight: 600;
}
.content {
  height: calc(100% - 48px);
  overflow-y: auto;
}
.search-banner {
  margin-top: 10px;
}
.banner-title {
  padding-left: 15px;
  padding-right: 15px;
  line-height: 32px;
  font-weight: bold;
}
.btn-banner {
  float: right;
  line-height: 32px;
  padding-left: 5px;
  padding-right: 5px;
  color: rgb(228, 222, 222);
}
.keyword-list {
  overflow: hidden;
  list-style: none;
  margin-top: 10px;
}
.keyword-item {
  box-sizing: border-box;
  float: left;
  padding-left: 15px;
  padding-right: 15px;
  line-height: 28px;
  display: inline-block;
  color: #333;
  background: rgb(216, 213, 213);
  margin: 10px;
  border-radius: 20px;
}
.one-line {
  overflow: hidden;
  text-overflow: ellipsis;
  word-wrap: break-word;
  white-space: nowrap;
}
img {
  width: 100%;
}
.pic {
  margin-top: 30px;
}
.pic h3 {
  margin-left: 15px;
  margin-bottom: 10px;
}
</style>