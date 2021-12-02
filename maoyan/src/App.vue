<template>
<transition
    :enter-active-class="`animate__animated ${enter}`"
    :leave-active-class="`animate__animated ${leave}`"
      >
    <router-view></router-view>
</transition>
</template>
<script>
export default {
    data() {
        return{
            enter:'animate__lideInLeft',
            leave:'animate__slideOutRights'
        }
    },
    watch:{
        $route(to,from){
            const toIndex=to.meta.index
            const fromIndex=from.meta.index
            console.log(toIndex,fromIndex)
            if(toIndex>fromIndex){
                this.enter='animate__slideInLeft',
                this.leave='animate__slideOutRight'
            }else{
                this.enter='animate__slideInRight',
                this.leave='animate__slideOutLeft'
            }
        }
    }
}
</script>
<style lang="stylus" scoped>
</style>