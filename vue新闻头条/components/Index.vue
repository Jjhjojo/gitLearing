<template>
  <div class="container">
    <span class="btn-enter" @click="enter">点击进入</span>
    <h1 class="tips">
      正在加载中...<span>{{ count }}</span>
    </h1>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      count: "",
    };
  },
  created() {
    this.countDown();
  },
  methods: {
    enter() {
      this.$router.push("/main");
    },
    countDown() {
      const TIME_COUNT = 3;
      if (!this.timer) {
        this.count = TIME_COUNT;
        this.timer = setInterval(() => {
          if (this.count > 1 && this.count <= TIME_COUNT) {
            //限制倒计时区间
            this.count--;
          } else {
            clearInterval(this.timer); //删除定时器
            this.timer = null;
            //跳转的页面写在此处
            this.$router.push("/main");
          }
        }, 1000);
      }
    },
  },
};
</script>

<style scoped>
.container {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: fixed;
  background-image: url(../assets/images/index.gif);
  background-position: center bottom;
  background-repeat: no-repeat;
}
.btn-enter {
  float: right;
  margin: 10px;
  padding: 5px;
  background: darkgrey;
  color: white;
  border-radius: 10px;
  font-size: 12px;
}
.tips {
  width: 80%;
  height: 5rem;
  text-align: center;
  position: absolute;
  left: 10%;
  top: 30%;
  transform: translateY(-50%);
}
</style>
