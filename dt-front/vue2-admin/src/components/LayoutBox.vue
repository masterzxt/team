<style scoped>
  .box-layout{
    display: table;
    width: 100%;
    height: 100%;
  }
  .box-layout .main{
    display: table-cell;
  }
</style>

<template>
  <div class="box-layout">
    <div class="main" :style="{ textAlign:h_align, verticalAlign:v_align }"><slot></slot></div>
  </div>
</template>

<script>
export default {
  name: 'layout-box',
  data () {
    return {
      'h_align': 'center',
      'v_align': 'middle'
    }
  }
}
</script>
