<style scoped>
  .HeaderBar .pad{height: 2rem;}
  .HeaderBar .pad.overlap{
    height:0;
  }
  .HeaderBar .header{
    padding: 0 3rem;
    height: 2rem;
    line-height: 2rem;
    position: fixed;
    width: 100%;
    min-width: 300px;
    z-index: 998;
    top: 0;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    text-align: center;
    font-size:.8rem;
    background-size: cover;
    background-position: 0 100%;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
  }
  .HeaderBar .header>.icon{
    font-family: "微软雅黑","icon" !important;
    width: 2rem;
    height: 2rem;
    position: absolute;
    top: 0;
    cursor: pointer;
    font-size: .65rem;
  }
  .HeaderBar .header>.icon.i-back{
    left: 0;
  }
  .main>.icon.r-btn{
    right: 0;
  }
</style>

<template>
  <div class="HeaderBar">
    <div class="header mw" :style="{ backgroundColor:bgColor,color:fgColor }">
      <div class="icon i-back" @click="l_btn_click"></div>
      <slot>{{ title }}</slot>
      <template v-if="rBtnIcon || rBtnText">
        <div class="icon r-btn" :class="['i-' + rBtnIcon]" @click="r_btn_click">{{ rBtnText }}</div>
      </template>
    </div>
    <div class="pad"></div>
  </div>
</template>

<script>
  /**
   * @param bgColor : s/m/l/xl/xxl 设置按钮大小
   * @param fgColor : value 设置按钮的圆角
   * @param rBtnIcon : bool/false 是否是块状按钮
   * @param rBtnText : /right/info/warn/error/blank 按钮的样子
   * @param rBtnClick : bool/false 是否禁用按钮
   */
  export default {
    name: 'HeaderBar',
    props: {
      bgColor: {default: '#479cff'},
      fgColor: {default: '#fff'},
      rBtnIcon: {default: ''},
      rBtnText: {default: ''}
    },
    computed: {
      title : function () {
        return document.title;
      }
    },
    methods: {
      l_btn_click: function () {
        history.go(-1);
      },
      r_btn_click: function () {
        this.$emit('rBtnClick');
      }
    }
  }
</script>
