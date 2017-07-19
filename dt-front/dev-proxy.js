const config = require('./dev.proxy.config');
const express = require('express');
var bodyParser = require("body-parser");
const path = require('path');
const proxy = require('http-proxy-middleware');//引入代理中间件
const watch = require('watch')

var names = process.argv.splice(2);
names.forEach(function (name) {
    run(name);
});

function run(name) {
    var app = express();
    app.use(bodyParser.urlencoded({ extended: false }));
    var _config = config[name];
    app.use(function (req, res, next) {
        try{
            let path = `./${_config.test}/${req.method.toLowerCase() + req.originalUrl}`;
            console.log('[API] ' + path);
            var api = require(path);
            console.log(req.body);
            res.end(JSON.stringify(api));
        }catch (e){
            next();
        }
    });

    var server = app.listen(_config.port, 'localhost', function () {
        const host = server.address().address;
        const port = server.address().port;
        console.log(`模拟 ${name} 服务器开启 http://${host}:${port}`);
    });

    watch.createMonitor(`./${_config.test}`, function (monitor) {
        monitor.on("changed", function (f) {
            console.log(`[MOD] 更新 api 文件 ${f}`);
        });
        monitor.on("removed", function (f, stat) {
            console.log(`[DEL] 删除 api 文件 ${f}`);
        });

        function delCache(f) {
            try{
                delete require.cache[require.resolve('./' + f)];
            }catch(e){

            }
        }
    })
}