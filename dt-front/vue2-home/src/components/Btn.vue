<style scoped>
  .Btn{
    color: #fff;
    background-color: #479CFF;
    text-align: center;
    padding: .65rem;
    cursor: pointer;
    transition: opacity .6s,background .6s;
    opacity: .85;
    min-width: 4rem;
    border: 1px solid #479CFF;
  }
  .Btn:hover {
    opacity: 1;
  }

  .Btn.disable {
    opacity: .62;
  }
  .Btn.ib{
    display: inline-block;
  }
  .Btn.s{
    font-size: .6rem;
    padding: .5rem;
  }
  .Btn.m{
    font-size: .7rem;
    padding: .58rem;
  }
  .Btn.l{
    font-size: .8rem;
  }
  .Btn.xl{
    font-size: .9rem;
    padding: .73rem;
  }
  .Btn.xxl{
    font-size: 1rem;
    padding: .8rem;
  }
  .Btn.right{
    background: #3c763d;
    border-color: #3c763d;
  }
  .Btn.info{
    background: #31708f;
    border-color: #31708f;
  }
  .Btn.warn{
    background: #8a6d3b;
    border-color: #8a6d3b;
  }
  .Btn.error{
    background: #a94442;
    border-color: #a94442;
  }
  .Btn.transparent{
    background: transparent;
    color: #333;
  }

</style>

<template>
  <div class="Btn"
       :class="[size,type,{ disable:isDisable,ib:!block,transparent:!opaque }]"
       @click="_click" :style="{ borderRadius:radius }">
    <template v-if="state">
      {{ state }}
    </template>
    <template v-else>
      <slot></slot>
    </template>
  </div>
</template>

<script>
  /**
   * @param size : s/m/l/xl/xxl 设置按钮大小
   * @param radius : value 设置按钮的圆角
   * @param block : bool/false 是否是块状按钮
   * @param type : /right/info/warn/error 按钮的样子
   * @param disable : bool/false 是否禁用按钮
   * @event click : 点击按钮的事件
   */
  export default {
    name: 'Btn',
    props: {
      size: {default: 'm'},
      opaque: {default: true},
      radius: {default: '2px'},
      block: {default: false},
      type: {default: ''},
      state: {default: ''},
      disable: {default: false}
    },
    computed: {
      isDisable : function () {
        return this.state || this.disable;
      }
    },
    methods: {
      _click: function () {
        (!this.isDisable) && this.$emit('click');
      }
    }
  }
</script>
