module.exports = {
    home: {
        port: 8000,
        api_root: 'http://localhost:8080/bt-cgi/',
        index: './vue2-home/index_prod.html',
        dist: 'vue2-home/dist'
    },
    admin: {
        port: 8001,
        api_root: 'http://admin.prod.com/api/',
        index: './vue2-admin/index_prod.html',
        dist: 'vue2-admin/dist'
    }
};