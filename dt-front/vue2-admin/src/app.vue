<style>
    @import 'styles/common.css';
</style>
<template>
    <div class="body">
        <bg></bg>
        <router-view class="page"></router-view>
    </div>
</template>
<script>
    import Bg from './components/Bg'
    export default {
        name: 'app',
        components: {
            'bg': Bg
        },
        data () {
            return {}
        },
        mounted () {

        },
        beforeDestroy () {

        },
        methods: {

        }
    }
</script>