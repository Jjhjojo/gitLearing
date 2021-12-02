<template>
  <div>
    <van-tabs>
      <van-tab title="账户登录">
        <van-form @submit="onSubmit">
          <van-field
            v-model="username"
            name="用户名"
            label="用户名"
            placeholder="手机号/密码/Email"
            :rules="[{ required: true, message: '请填写用户名' }]"
          />
          <van-field
            v-model="password"
            type="password"
            name="密码"
            label="密码"
            placeholder="密码"
            :rules="[{ required: true, message: '请填写密码' }]"
          />
          <div style="margin: 16px">
            <van-button color="#cd4c42" round block>登录</van-button>
          </div>
        </van-form>
      </van-tab>
      <van-tab title="手机验证码登录">
          <van-form @submit="onSubmit">
          <van-field
            v-model="number"
            name="手机号"
            label="手机号"
            placeholder="请输入手机号"
            :rules="[{ required: true, message: '请输入手机号' }]"
          />
        <van-field
          v-model="sms"
          center
          clearable
          label="短信验证码"
          placeholder="请输入短信验证码"
          :rules="[{ required: true, message: '请输入短信验证码' }]"
        >
        
          <template #button>
            <van-button size="small" type="danger">发送验证码</van-button>
          </template>
          
        </van-field>
        </van-form>
      </van-tab>
    </van-tabs>
  </div>
</template>
<script>
import Vue from "vue";
import { Form } from "vant";
import { Field } from "vant";
import { Button } from "vant";
import { Tab, Tabs } from "vant";

Vue.use(Tab);
Vue.use(Tabs);
Vue.use(Button);
Vue.use(Form);
Vue.use(Field);
export default {
  data() {
    return {
      username: "",
      password: "",
      number: "",
      sms:""
    };
  },
  methods: {
    onSubmit(values) {
      console.log("submit", values);
    }
  },
};
</script>
<style lang="stylus" scoped></style>