const routers = [
    {
        path: '/',
        component: (resolve) => require(['./views/index/Index.vue'], resolve)
    },
    {
        path: '/login',
        component: (resolve) => require(['./views/index/Login.vue'], resolve)
    }
];
export default routers;