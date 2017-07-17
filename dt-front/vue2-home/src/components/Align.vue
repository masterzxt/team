<style scoped>
  .Align{
    display: table;
    width: 100%;
    height: 100%;
  }
  .Align .main{
    display: table-cell;
    position: relative;
  }
</style>

<template>
  <div class="Align">
    <div class="main"
         :style="{ textAlign:h_align, verticalAlign:v_align, top: offset_y, left: offset_x }">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Align',
  props: {
    h_align: {default: 'center'},
    v_align: {default: 'middle'},
    offset_x: {default: '0'},
    offset_y: {default: '0'}
  }
}
</script>
