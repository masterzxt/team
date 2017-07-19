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
                <input type="text" v-model="mobile" placeholder="请输入手机号码">
            </div>
            <div class="input">
                <input type="password" v-model="pwd" placeholder="请输入登录密码">
            </div>
            <div class="input">
                <input type="password" v-model="repwd" placeholder="请确认登录密码">
            </div>
            <div class="input verify">
                <input type="text" v-model="verify" placeholder="请输入短信验证码">
                <btn type="blank" :state="stateSendVerify" size="s" opaque @click="sendVerify">发送验证码</btn>
            </div>
            <div class="row">
                <check v-model="agree">我已同意</check>
                <router-link to="/user/agreement">《免责协议》</router-link>
            </div>
            <div class="err-msg">{{ errmsg }}</div>
            <btn block="true" :state="stateReg" :disable="!agree" @click="reg">下一步</btn>
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
                mobile: '',      //form 手机号
                pwd: '',         //form 密码
                repwd: '',       //form 确认密码
                verify: '',      //form 验证码
                agree: false,   //同意条款
                errmsg: '',  //错误显示
                stateSendVerify: false,    //按钮状态
                stateReg: false    //按钮状态
            };
        },
        methods: {
            sendVerify: function () {
                this.stateSendVerify = '短信发送中...';
                this.errmsg = '';
                let that = this;
                Util.post('reg', {
                    mobile: this.mobile,
                    pwd: this.pwd,
                    repwd: this.repwd,
                    verify: this.verify
                }).catch(function () {
                    that.stateSendVerify = false;
                    Util.msg('短信发送失败');
                }).then(function (res) {
                    if(res || true){
                        let t = 60;
                        let timer = setInterval(function () {
                            if (t > 0) {
                                that.stateSendVerify = t-- + ' 秒后可重新发送';
                            }else{
                                clearInterval(timer);
                            }
                        }, 1000);
                    }
                })
            },
            reg: function () {
                this.stateReg = '请稍等...';
                this.errmsg = '';
                let that = this;
                Util.post('reg',{
                    mobile: this.mobile,
                    pwd: this.pwd,
                    repwd: this.repwd,
                    verify: this.verify
                }).catch(function (res) {
                    that.stateReg = false;
                    if (res instanceof Error) {
                        that.errmsg = res.message;
                    } else {
                        that.errmsg = "未知错误"
                    }
                })
            }
        }
    };
</script>