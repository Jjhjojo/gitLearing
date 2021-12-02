<template>
  <div class="home-wrap">
    <header>猫眼电影</header>
    <transition 
      enter-active-class="animate__animated animate__fadeIn"
      leave-active-class="animate__animated animate__fadeOut"
    >
      <router-view></router-view>
    </transition>
    <nav class="tabber">
      <ul>
        <van-tabbar 
        v-model="active"
        active-color="#cd4c42"
        inactive-color="#666"
        router
        >
          <van-tabbar-item to="/home/movies">
            <span>电影</span>
            <template #icon="">
              <van-icon class-prefix="iconfont-nav" name="movie" />
            </template>
          </van-tabbar-item>
          <van-tabbar-item to="/home/theaters">
            <span>影院</span>
            <template #icon="">
              <van-icon class-prefix="iconfont-nav" name="theater" />
            </template>
          </van-tabbar-item>
          <van-tabbar-item to="/home/profile">
            <span>我的</span>
            <template #icon="">
              <van-icon class-prefix="iconfont-nav" name="profile" />
            </template>
          </van-tabbar-item>
        </van-tabbar>
      </ul>
    </nav>
  </div>
</template>
<script>
import Vue from "vue";
import { Icon, Tabbar, TabbarItem } from "vant";

Vue.use(Tabbar);
Vue.use(TabbarItem);
Vue.use(Icon);
export default {
  data() {
    return {
       active: 0,
    };
  },
};
</script>
<style lang="stylus" scoped>
@import '~@/assets/stylus/icon.styl';

.home-wrap {
  display: flex;
  flex-direction: column;
  height: 100%;

  header {
    color: #FFF;
    background: #cd4c42;
    height: 0.44rem;
    font-size: 0.16rem;
    font-weight: bold;
    text-align: center;
    line-height: 0.44rem;

    &::before {
      content: '\e60c';
      font-family: iconfont-nav;
    }

    &::after {
      content: '\e60c';
      font-family: iconfont-nav;
    }
  }
}

.tabber {
  height: 0.44rem;
  // border1px(1px 0 0 0);

  // ul {
  //   display: flex;

  //   li {
  //     flex: 1;
  //     display: flex;
  //     flex-direction: column;
  //     justify-content: center;
  //     align-items: center;
  //     padding-top: 0.06rem;
  //     color: #999;

  //     b {
  //       font-size: 0.26rem;
  //       font-weight: normal;
  //       line-height: 0.2rem;
  //     }

  //     span {
  //       font-size: 0.12rem;
  //       transform: scale(0.9);
  //     }

  //     &.active {
  //       color: #cd4c42;
  //     }
  //   }
  // }
}
</style>