const routers = [
    { path: '/', redirect: '/main' },
    {
        path: '/main',
        meta: {
            title: '首页',
            checkLogin: true
        },
        component: (resolve) => require(['./views/index.vue'], resolve),
        children:[
        ]
    },
    
    /*user*/
    {
        path: '/user/login',
        meta: {
            title: '登录'
        },
        component: (resolve) => require(['./views/user/login.vue'], resolve)
    },
    {
        path: '/user/reg',
        meta: {
            title: '注册'
        },
        component: (resolve) => require(['./views/user/reg.vue'], resolve)
    },
    {
        path: '*',
        meta: {
            title: '404'
        },
        component: (resolve) => require(['./views/empty.vue'], resolve)
    }
];
export default routers;