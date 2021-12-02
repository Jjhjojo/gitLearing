<template>
  <div>
    <ul>
      <li v-for="(product,index) in products" :key="index">
        {{product.name}}:{{product.quantity}}x{{product.price}}={{product.quantity*product.price}}
      </li>
    </ul>
    <div>总价：{{totalPrice}}</div>
  </div>
</template>

<script>
import { mapState,mapGetters } from "vuex";
export default {
  computed: {
    ...mapState("cart", {
      products: "items",
    }),
    ...mapGetters('cart',['totalPrice'])
  },
};
</script>

<style>
</style>