const config = require('./config');
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

    app.use('/dist', express.static(path.join(__dirname, _config.dist)));

    const apiProxy = proxy('/bt-cgi', {
        target: _config.api_root,
        changeOrigin: true,
        secure: false,
        pathRewrite: {'^/bt-cgi': ''}
    });

    app.use('/bt-cgi/', apiProxy);

    app.use('/', function (req, res) {
        res.sendFile(path.join(__dirname, _config.index));
    });

    var server = app.listen(_config.port, '0.0.0.0', function () {
        const host = server.address().address;
        const port = server.address().port;
        console.log(`${name} 服务开启 http://${host}:${port}`);
    })
}