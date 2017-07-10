<style scoped>
  .page-login{
    position: fixed;
    height: 100%;
    width: 100%;
  }
  .page-login .main{
    max-width: 300px;
    margin: auto;
  }
  .page-login .title{
    text-align: center;
    padding: 15px;
  }
  .page-login .box-login{
    margin-bottom: 50px;
  }
</style>

<template>
  <div class="page-login">
    <layout-box class="main">
      <div class="title">后台登录</div>
      <login-box></login-box>
    </layout-box>
  </div>
</template>

<script>
  import LoginBox from '../../components/LoginBox'
  import LayoutBox from '../../components/LayoutBox'

  export default {
    components: {
      'login-box': LoginBox,
      'layout-box': LayoutBox
    },
    data () {
      return {
      }
    }
  }
</script>
