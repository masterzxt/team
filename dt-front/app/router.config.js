import App from './App.vue'
import Login from "./login/login.vue"
import Index from "./index/index.vue"

export default[
    {
        path: "/login",   //登陆页面
        component: Login
    },
    {
        path: '/index', //首页
        component: Index
               // redirect: "/login"
    }
]

