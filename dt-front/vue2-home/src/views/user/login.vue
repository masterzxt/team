<style scoped>
    .-user-login {
        margin: 0 auto;
        padding-top: 2rem;
    }

    .head {
        width: 5rem;
        height: 5rem;
        background: #e5e5e5;
        border-radius: 50%;
        margin: 0 auto 2rem;
    }

    .input {
        border-bottom: 1px solid #bbb;
        padding: .6rem .6rem .6rem 2rem;
        color: #555;
        position: relative;
        margin-top: .5rem;
    }

    .input input {
        border: none;
        display: block;
        width: 100%;
        color: #333;
    }

    .input .icon {
        position: absolute;
        left: 0;
        width: 2rem;
        height: 2.4rem;
        line-height: 2.4rem;
        top: 0;
        font-size: 1rem;
        text-align: center;
        transition: color 1s;
    }

    .input.focus .icon {
        color: #479CFF;
    }

    .err-msg {
        color: red;
        text-align: right;
        margin-top: .5rem;
        font-size: .7rem;
        height: 1rem;
        line-height: 1rem;
        overflow: hidden;
    }

    .btn {
        margin-top: 15px;
        opacity: .85;
    }

    .link {
        margin-top: 1rem;
        font-size: .7rem;
    }
</style>
<template>
    <div class="-user-login main-pad main-mw">
        <img class="head"/>
        <div class="input" :class="{ focus:current=='user' }">
            <i class="icon i-user"></i>
            <input class="user" v-model="username" type="text" placeholder="账号" @focus="focus">
        </div>
        <div class="input" :class="{ focus:current=='pwd' }">
            <i class="icon i-pwd"></i>
            <input class="pwd" v-model="password" type="password" placeholder="密码" @focus="focus">
        </div>
        <div class="err-msg">{{ errmsg }}</div>
        <btn :state="state" block="true" size="l" @click="login">登录</btn>
        <router-link class="fl link" to="/user/reg">用户注册</router-link>
        <router-link class="fr link" to="/user/repwd">忘记密码</router-link>
    </div>
</template>
<script>
    import Util from '../../libs/util';
    import Btn from '../../components/Btn';
    import qs from 'qs';

    export default {
        components: {
            'btn': Btn
        },
        data(){
            return {
                username: '',           //form 用户账号
                password: '',            //form 用户密码
                errmsg: '',         //错误显示
                state: false,    //请求是否在提交中
                current: ''
            };
        },
        methods: {
            login: function () {
                this.state = '请稍等...';
                this.errmsg = '';
                let that = this;
                //登录接口
                Util.post('/login',qs.stringify({
                    username:this.username,
                    password:this.password
                }),{
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).catch(function (res) {
                    if (res instanceof Error) {
                        that.errmsg = res.message;
                    } else {
                        that.errmsg = "未知错误"
                    }
                }).then(function (res) {
                    console.log(res);
                    that.state = false;
                    if(res.data.code == 0){
                        //成功  跳转
                        that.errmsg = res.data.msg;
                    }else{
                        //失败
                        that.errmsg = res.data.msg;
                    }
                });
            },
            focus: function (e) {
                this.current = e.target.className;
            }
        }
    };
</script>