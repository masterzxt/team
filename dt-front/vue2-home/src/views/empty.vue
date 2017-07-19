<style scoped>
    .-empty{
        text-align: center;
        height: 100%;
        position: absolute;
        width: 100%;
        max-width: 844px;
    }
    .-empty .i-404{
        font-size: 6rem;
        color: #666;
    }
    .-empty p{
        margin: 1rem 0;
        color: #333;
    }
</style>
<template>
    <div class="-empty">
        <align offset_y="-2rem">
            <i class="icon i-404"></i>
            <p>您访问的页面不存在</p>
            <btn @click="back">返回上一页</btn>
        </align>
    </div>
</template>
<script>
    import Align from '../components/Align';
    import Btn from '../components/Btn';
    export default {
        components: {
            'align': Align,
            'btn': Btn
        },
        methods: {
            back: function () {
                history.go(-1)
            }
        }
    };
</script>