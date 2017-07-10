<style scoped>
  .input{
    border: 1px solid #e8e8e8;
    padding: 12px 12px 12px 40px;
    color: #555;
  }
  .box-login .input{
    background: #fff;
    margin-top: -1px;
    position: relative;
  }
  .input input{
    border: none;
    display: block;
    width: 100%;
    color: #333;
  }
  .input .icon{
    position: absolute;
    left: 0;
    width: 40px;
  }
  .input.focus .icon{
    color: #2d8cf0;
  }
  .error{
    color: red;
    text-align: right;
    margin-top: 8px;
    font-size: 12px;
    height: 16px;
    line-height: 16px;
    overflow: hidden;
  }
  .btn{
    color: #fff;
    background-color: #2d8cf0;
    border-color: #2d8cf0;
    margin-top: 15px;
    padding: 12px 0;
    cursor: pointer;
    transition: opacity .6s,background .6s;
    opacity: .85;
  }
  .btn:hover{
    opacity: 1;
  }
  .btn.loading{
    opacity: .62;
  }
</style>

<template>
  <div class="box-login">
    <div class="input" :class="{ focus:current=='account' }">
      <i class="icon i-account"></i>
      <input class="account" type="text" placeholder="账号" @focus="focus">
    </div>
    <div class="input" :class="{ focus:current=='password' }">
      <i class="icon i-password"></i>
      <input class="password" type="password" placeholder="密码" @focus="focus">
    </div>
    <div class="input" :class="{ focus:current=='verify' }">
      <i class="icon i-verify"></i>
      <input class="verify" type="text" placeholder="验证码" @focus="focus">
    </div>
    <div class="error">密码错误</div>
    <div class="btn" @click="login" :class="{ loading:loading }">
      <template v-if="loading">
        请稍等...
      </template>
      <template v-else>
        登录
      </template>
    </div>
  </div>
</template>

<script>
export default {
  name: 'login-box',
  data () {
    return {
      loading: false,
      current: ''
    }
  },
  methods: {
    login: function () {
      if (!this.loading) {
        this.loading = true
        let that = this
        setTimeout(function () {
          that.loading = false
        }, 1000)
      }
    },
    focus: function (e) {
      this.current = e.target.className
    }
  }
}
</script>
