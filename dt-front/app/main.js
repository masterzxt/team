import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import Vuex from 'vuex'
import routes  from './router.config'

const scrollBehavior = (to, from, savedPosition) => {
    if (savedPosition) {
        return savedPosition
    } else {
        return { x: 0, y: 0 }
    }
}
Vue.use(Vuex)
Vue.use(VueRouter)
// 创建一个路由器实例,并且配置路由规则
const router = new VueRouter({
    mode: 'history',
    base: __dirname,
    linkActiveClass:'link-active',
    scrollBehavior,
    routes
});
//创建和挂载根实例a
const pp = new Vue({
    router: router,
    render: h => h(App)
}).$mount('#app');