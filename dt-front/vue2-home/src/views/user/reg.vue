<style scoped>
    input[type=text],input[type=password] {
        border: none;
        border-bottom: 1px solid #bbb;
        padding: .5rem;
        position: relative;
        margin-top: .5rem;
        display: block;
        color: #333;
        width: 100%;
    }
    .verify {
        padding-right: 7rem;
        position: relative;
    }
    .verify .Btn{
        position: absolute;
        right: 0;
        bottom: .2rem;
        width: 6.5rem;
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
    .form{
        margin: 2rem auto 0;
    }
    .Check{
        font-size: .7rem;
        display: inline-block;
    }
    .Btn{
        margin-top: .5rem;
    }
</style>
<template>
    <div class="-user-reg">
        <header-bar></header-bar>
        <div class="form main-pad main-mw">
            <div class="input">
                <input type="text" v-model="username" placeholder="请输入用户名">
            </div>
            <div class="input">
                <input type="password" v-model="password" placeholder="请输入登录密码">
            </div>
            <div class="input">
                <input type="password" v-model="password2" placeholder="请确认登录密码">
            </div>
            <!--<div class="input verify">
                <input type="text" v-model="verify" placeholder="请输入短信验证码">
                <btn type="blank" :state="stateSendVerify" size="s" opaque @click="sendVerify">发送验证码</btn>
            </div>-->
            <div class="row">
                <check v-model="agree">我已同意</check>
                <router-link to="/user/agreement">《免责协议》</router-link>
            </div>
            <div class="err-msg">{{ errmsg }}</div>
            <btn block="true" :state="stateReg" :disable="!agree" @click="reg">注册</btn>
        </div>
    </div>
</template>
<script>
    import Util from '../../libs/util';
    import HeaderBar from '../../components/HeaderBar';
    import Btn from '../../components/Btn';
    import Check from '../../components/Check';

    export default {
        components: {
            'header-bar': HeaderBar,
            'btn': Btn,
            'check': Check
        },
        data: function () {
            return {
                username: '',      //form 用户名
                password: '',         //form 密码
                password2: '',       //form 确认密码
                agree: false,   //同意条款
                errmsg: '',  //错误显示
                stateReg: false    //按钮状态
            };
        },
        methods: {
            reg: function () {
                this.stateReg = '请稍等...';
                this.errmsg = '';
                let that = this;
                Util.post('reg',{
                    username: this.username,
                    password: this.password,
                    password2: this.password
                }).catch(function (res) {
                    that.stateReg = false;
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
            }
        }
    };
</script>