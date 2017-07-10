<style scoped>
  canvas{
    position: fixed;
    left: 0;
    top: 0;
  }
</style>

<template>
  <canvas></canvas>
</template>

<script>
export default {
  name: 'bg',
  mounted: function () {
    // 定义画布宽高和生成点的个数
    var WIDTH
    var HEIGHT
    var POINT = 0
    var rate = 1
    var canvas = this.$el
    var context = canvas.getContext('2d')
    var circleArr = []

    function resize () {
      WIDTH = window.innerWidth
      HEIGHT = window.innerHeight
      POINT = WIDTH * HEIGHT >> 15
      canvas.width = WIDTH
      canvas.height = HEIGHT
      circleArr = []
      // context.fillStyle = 'rgba(0,0,0,0.1)'
      for (var i = 0; i < POINT; i++) {
        var circle = new Circle(rN(WIDTH), rN(HEIGHT), rN(15, 2), rN(10, -10) / 40, rN(10, -10) / 40)
        circleArr.push(circle)
        drawCricle(context, circle)
      }
    }

    // 线条：开始xy坐标，结束xy坐标，线条透明度
    function Line (x, y, _x, _y, o) {
      this.beginX = x
      this.beginY = y
      this.closeX = _x
      this.closeY = _y
      this.o = o
    }

    // 点：圆心xy坐标，半径，每帧移动xy的距离
    function Circle (x, y, r, moveX, moveY) {
      this.x = x
      this.y = y
      this.r = r
      this.moveX = moveX
      this.moveY = moveY
      this.color = {
        R: rC(),
        G: rC(),
        B: rC()
      }
      this.bgcolor = 'rgba(' + this.color.R + ',' + this.color.G + ',' + this.color.B + ',' + Math.random() * 0.38 + ')'
    }

    // 生成max和min之间的随机数
    function rN (max, _min) {
      var min = arguments[1] || 0
      return Math.random() * (max - min + 1) + min | 0
    }

    function rC () {
      return rN(0, 255)
    }

    // 绘制原点
    function drawCricle (cxt, circle) {
      cxt.fillStyle = circle.bgcolor
      cxt.beginPath()
      cxt.arc(circle.x, circle.y, circle.r, 0, 2 * Math.PI)
      cxt.closePath()
      cxt.fill()
    }

    // 绘制线条
    function drawLine (cxt, c1, c2, o) {
      var line = new Line(c1.x, c1.y, c2.x, c2.y, o)
      cxt.beginPath()
      cxt.strokeStyle = 'rgba(0,0,0,' + o + ')'
      cxt.moveTo(line.beginX, line.beginY)
      cxt.lineTo(line.closeX, line.closeY)
      cxt.closePath()
      cxt.stroke()
    }

    // 初始化生成原点
    function init () {
      /* document.onmousemove = function (e) {
        rate = (e.pageX / WIDTH - 0.5).toFixed(2) * 3
        console.log(rate)
      } */
      context.strokeStyle = 'rgba(0,0,0,0.2)'
      context.strokeWidth = 1
      resize()
      setInterval(function () {
        for (var i = 0; i < POINT; i++) {
          var cir = circleArr[i]
          cir.x += cir.moveX * rate
          cir.y += cir.moveY * rate
          if (cir.x > WIDTH) cir.x = 0
          else if (cir.x < 0) cir.x = WIDTH
          if (cir.y > HEIGHT) cir.y = 0
          else if (cir.y < 0) cir.y = HEIGHT
        }
        draw()
      }, 16)
      window.onresize = resize
    }

    // 每帧绘制
    function draw () {
      var i
      context.clearRect(0, 0, WIDTH, HEIGHT)
      for (i = 0; i < POINT; i++) {
        drawCricle(context, circleArr[i])
      }
      for (i = 0; i < POINT; i++) {
        for (var j = 0; j < POINT; j++) {
          if (i + j < POINT) {
            var A = circleArr[i + j].x - circleArr[i].x
            var B = circleArr[i + j].y - circleArr[i].y
            var lineLength = Math.sqrt(A * A + B * B)
            var C = 1 / lineLength * 8 - 0.01
            var lineOpacity = C > 0.03 ? 0.03 : C
            if (lineOpacity > 0) {
              drawLine(context, circleArr[i], circleArr[i + j], lineOpacity)
            }
          }
        }
      }
    }

    init()
  }
}
</script>
