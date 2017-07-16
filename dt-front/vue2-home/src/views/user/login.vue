<style scoped>
    .-user-login{
        max-width: 521px;
        margin: 0 auto;
        padding: 0 1.2rem;
    }
    .head{
        width: 5rem;
        height: 5rem;
        background: #e5e5e5;
        border-radius: 50%;
        margin: 2rem auto;
    }
    .input{
        border-bottom: 1px solid #bbb;
        padding: 12px 12px 12px 40px;
        color: #555;
        position: relative;
        margin-top: .5rem;
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
        height: 48px;
        line-height: 48px;
        top: 0;
        font-size: 1rem;
        text-align: center;
        transition: color 1s;
    }
    .input.focus .icon{
        color: #479CFF;
    }
    .err-msg{
        color: red;
        text-align: right;
        margin-top: .5rem;
        font-size: .7rem;
        height: 1rem;
        line-height: 1rem;
        overflow: hidden;
    }
    .btn{
        margin-top: 15px;
        opacity: .85;
    }
    .btn:hover{
        opacity: 1;
    }
    .btn.loading{
        opacity: .62;
    }
    .link{
        margin-top: 1rem;
        font-size: .7rem;
    }
</style>
<template>
    <div class="-user-login">
        <img class="head" />
        <div class="input" :class="{ focus:current=='user' }">
            <i class="icon i-user"></i>
            <input class="user" v-model="user" type="text" placeholder="账号" @focus="focus">
        </div>
        <div class="input" :class="{ focus:current=='pwd' }">
            <i class="icon i-pwd"></i>
            <input class="pwd" v-model="pwd" type="password" placeholder="密码" @focus="focus">
        </div>
        <div class="err-msg">{{ errmsg }}</div>
        <div class="btn" @click="login" :class="{ loading:loading }">
            <template v-if="loading">
                请稍等...
            </template>
            <template v-else>
                登录
            </template>
        </div>
        <router-link class="fl link" to="/user/reg">用户注册</router-link>
        <router-link class="fr link" to="/user/repwd">忘记密码</router-link>
    </div>
</template>
<script>
    import Util from '../../libs/util';
    export default {
        name: 'login-box',
        data () {
            return {
                user: '',           //用户账号
                pwd: '',            //用户密码
                errmsg: '',         //错误显示
                loading: false,    //请求是否在提交中
                current: ''
            }
        },
        methods: {
            login: function () {
                if (!this.loading) {
                    this.loading = true
                    this.errmsg = '';
                    let that = this

                    Util.ajax.post('/ajaxLogin',{
                        username: that.user,
                        password: that.pwd
                    }).catch(function (res) {
                        that.loading = false
                        if (res instanceof Error) {
                            that.errmsg = res.message
                            console.log('Error', res.message)
                        } else {
                            console.log(res.data);
                            console.log(res.status);
                            console.log(res.headers);
                            console.log(res.config);
                        }
                    }).then(function (res) {
                        console.log(res);
                        console.log(res.data);
                        that.loading = false
                        that.errmsg = "密码错误"
                    });
                }
            },
            focus: function (e) {
                this.current = e.target.className
            }
        }
    }
</script>