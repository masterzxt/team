<template>
    <div id="app">
        <h3>顶部栏</h3>
        {{msg}}
        <!-- 路由匹配到的组件将渲染在这里 -->
        <router-view></router-view>
        <h3>底部栏</h3>
        <router-link to="/index">Go to index</router-link>
        <router-link to="/login">Go to login</router-link>
    </div>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        name: 'app',
        data () {
            return {
                msg: 'Welcome to Vue.js App',
            }
        },
        methods: {
        }
    }
</script>

<style scope>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }

    h1, h2 {
        font-weight: normal;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
    #pageContainer{
        border:1px solid #0ff;
    }
</style>
