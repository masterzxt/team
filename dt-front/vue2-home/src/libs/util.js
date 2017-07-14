import axios from 'axios';
import env from '../config/env';
import cookies from 'vue-cookie';
let util = {};
util.title = function(title) {
    //title = title ? title + ' - Home' : 'iView project';
    window.document.title = title;
};
util.ajax = axios.create({
    baseURL: `/api/`,
    timeout: 30000
});

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

export default util;