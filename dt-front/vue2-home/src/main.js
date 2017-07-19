import Vue from 'vue';
import VueRouter from 'vue-router';
import Routers from './router';
import Util from './libs/util';
import App from './app.vue';

Vue.use(VueRouter);

// 路由配置
const RouterConfig = {
    mode: 'history',
    routes: Routers
};
const router = new VueRouter(RouterConfig);

router.beforeEach((to, from, next) => {
    console.log(to);
    if (to.meta.checkLogin && !Util.cookie('uid')) {
        next({
            path: '/user/login',
            query: {redirect: to.fullPath}
        });
    } else {
        //iView.LoadingBar.start();
        Util.title(to.meta.title);
        next();
    }
});

router.afterEach(() => {
    //iView.LoadingBar.finish();
    window.scrollTo(0, 0);
});



new Vue({
    el: '#app',
    router: router,
    render: h => h(App)
});