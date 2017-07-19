<style scoped>
  .Check{
    cursor: pointer;
    transition: opacity .6s,background .6s;
    opacity: .85;
  }
  .Check:hover {
    opacity: 1;
  }
  .Check .icon{
    color: #479CFF;
    font-size: 1.1rem;
    line-height: 1.1rem;
  }
  .Check.disable {
    opacity: .62;
  }
</style>

<template>
  <div class="Check" @click="click">
    <i class="icon" :class="['i-check-' + (checked?'on':'off')]"></i>
    <slot></slot>
  </div>
</template>

<script>
  /**
   * @param value : bool/false 是否勾选
   * @param disable : bool/false 是否禁用按钮
   * @Input
   */
  export default {
    name: 'Check',
    data: function () {
      return {
        checked: false
      };
    },
    props: {
      value: {default: false},
      disable: {default: false}
    },
    watch: {
      value(val) {
        this.checked = val;
      },
      checked(val){
        this.$emit("input",val);
      }
    },
    methods: {
      click: function () {
        this.checked = !this.checked;
      }
    }
  }
</script>
