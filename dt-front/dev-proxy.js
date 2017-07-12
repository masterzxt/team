const config = require('./dev.proxy.config');
const express = require('express');
const path = require('path');
const proxy = require('http-proxy-middleware');//引入代理中间件

var names = process.argv.splice(2);
names.forEach(function (name) {
    run(name);
});

function run(name) {
    var app = express();
    var _config = config[name];
    app.use(function (req, res, next) {
        try{
            //console.log(`./${_config.test}/${req.method.toLowerCase() + req.originalUrl}`);
            var api = require(`./${_config.test}/${req.method.toLowerCase() + req.originalUrl}`);
            res.end(JSON.stringify(api));
        }catch (e){
            next();
        }
    });

    var server = app.listen(_config.port, 'localhost', function () {
        const host = server.address().address;
        const port = server.address().port;
        console.log(`模拟 ${name} 服务器开启 http://${host}:${port}`);
    })
}