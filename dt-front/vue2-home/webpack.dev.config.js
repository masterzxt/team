const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const merge = require('webpack-merge');
const webpackBaseConfig = require('./webpack.base.config.js');
const fs = require('fs');
const proxy_config = require('../dev.proxy.config').home;

fs.open('./src/config/env.js', 'w', function(err, fd) {
    const buf = 'export default "development";';
    fs.write(fd, buf, 0, buf.length, 0, function(err, written, buffer) {});
});

module.exports = merge(webpackBaseConfig, {
    devtool: '#source-map',
    output: {
        publicPath: '/dist/',
        filename: '[name].js',
        chunkFilename: '[name].chunk.js'
    },
    plugins: [
        new ExtractTextPlugin({
            filename: '[name].css',
            allChunks: true
        }),
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendors',
            filename: 'vendors.js'
        }),
        new HtmlWebpackPlugin({
            filename: '../index.html',
            template: './src/template/index.ejs',
            inject: false
        })
    ],
    devServer:{
        // webpack-dev-server options
        contentBase: "./",
        // Can also be an array, or: contentBase: "http://localhost/",
        hot: true,
        open: true,
        // Set this if you want to enable gzip compression for assets
        compress: true,
        port: 8000,
        proxy: {
            '/bt-cgi': {
                // 接口代理
                target: `http://${proxy_config.host}:${proxy_config.port + proxy_config.path}`,
                changeOrigin: true,
                secure: false,
                pathRewrite: {'^/bt-cgi': ''}
            }
        },
        historyApiFallback: true
    }
});