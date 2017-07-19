import axios from 'axios';
import env from '../config/env';
import cookies from 'vue-cookie';
import qs from 'qs';

let util = {};
util.title = function(title) {
    //title = title ? title + ' - Home' : 'iView project';
    window.document.title = title;
};

let ajax = axios.create({
    baseURL: `/tb-cgi/`,
    timeout: 30000,
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
});

util.get = ajax.get;

util.post = function (path,data,opt) {
    return ajax.post(path, qs.stringify(data), opt);
};

util.cookie = function (name, value, daysOrOptions) {
    if (arguments.length == 1) {
        return cookies.get(name);
    } else {
        if (value != null) {
            return cookies.set(name, value, daysOrOptions);
        } else {
            return cookies.delete(name, daysOrOptions);
        }
    }
};

util.msg = function (s,ok,cancel) {
    if (arguments.length > 1) {
        confirm(s) ? ok && ok() : cancel && cancel();
    } else {
        alert(s)
    }
};

export default util;