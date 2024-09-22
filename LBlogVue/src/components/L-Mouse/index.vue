<template>
  <canvas id="canvas" class="canvas"></canvas>
</template>

<script setup lang="js">
import { onMounted } from 'vue';
let mouse, originx, originy, int, cvs;

// Safari doesn't support EventTarget
var EventTarget = EventTarget || false;

// addEventListener shorthand
if (EventTarget) {
  EventTarget.prototype.evt = function (event, callback) {
    return this.addEventListener(event, callback);
  };
} else {
  window.evt = function (event, callback) {
    return this.addEventListener(event, callback);
  };
  document.evt = function (event, callback) {
    return this.addEventListener(event, callback);
  };
  Element.prototype.evt = function (event, callback) {
    return this.addEventListener(event, callback);
  };
}

// getElementById shorthand
function $(elemId) {
  return document.getElementById(elemId);
}

function init() {
  cvs = $('canvas');

  resizeCanvas(cvs);

  window.evt('resize', resizeCanvas, false);
  window.evt('mousemove', function (e) {
    mouse = getMousePos(cvs, e);
    originx = mouse.x;
    originy = mouse.y;
  });
  // window.evt("touchmove", function (e) {
  //     originx = e.originalEvent.touches[0].pageX;
  //     originy = e.originalEvent.touches[0].pageY;
  // });

  var emit = new Emitter(0, 0, 50);

  animateCanvas(cvs, function () {
    emit.animate();
  });
}

// Individual particle
class Point {
  constructor(x, y, canvas, radius) {
    this.canvas = canvas || cvs;
    this.x = x || 0;
    this.y = y || 0;
    this.vx = 0;
    this.vy = 0;
    this.speed = Math.random() * 0.5 + 0.2;
    this.angle = Math.random() * 360;
    this.diaSet = radius || 2 + Math.random() * 10;
    this.radius = this.diaSet;
    this.age = 0;
    let hue = Math.floor(Math.random() * 360);
    this.fill = 'hsl(' + hue + ', 95%, 70%)';
    this.line = Math.random() > 0.5 ? true : false;
  }

  emit(life) {
    let s = this.speed * 2;
    this.angle += Math.random() * 10 - 5;
    this.x += s * Math.cos((this.angle * Math.PI) / 180);
    this.y += s * Math.sin((this.angle * Math.PI) / 180);
    this.age += 1 / life;
    this.boundary();
  }

  boundary() {
    if (this.x < 0) {
      this.x = this.canvas.width;
    }
    if (this.x > this.canvas.width) {
      this.x = 0;
    }
    if (this.y < 0) {
      this.y = this.canvas.height;
    }
    if (this.y > this.canvas.height) {
      this.y = 0;
    }
  }

  shrink(life) {
    this.radius = (1 - this.age) * this.diaSet;
  }

  draw() {
    let ctx = this.canvas.getContext('2d'),
      x = this.x,
      y = this.y,
      radius = this.radius,
      age = this.age;

    ctx.beginPath();
    ctx.fillStyle = this.fill;
    ctx.strokeStyle = this.fill;
    ctx.lineWidth = 2;
    ctx.arc(x, y, radius, 0, 2 * Math.PI);
    ctx.closePath();

    this.line !== true ? ctx.fill() : ctx.stroke();
  }
}

class ParticleGroup {
  setPosition(x, y) {
    this.x = x;
    this.y = y;
  }

  getPosition() {
    return {
      x: this.x,
      y: this.y
    };
  }

  spawn(x, y, amount = 1, radius = false) {
    var arr = [];
    for (let i = 0; i < amount; i++) {
      arr.push(new Point(x, y, cvs, radius));
    }
    return arr;
  }
}

// Particle Emitter
class Emitter extends ParticleGroup {
  constructor(x=0, y=0, life = 20, mouse = true, radius = false) {
    super();
    this.mouse = mouse;
    this.particles = [];
    this.x = x;
    this.y = y;
    this.life = life;
    this.canvas = cvs;
    this.radius = radius;
  }

  animate() {
    let particles = this.particles;
    if (this.mouse) {
      this.setPosition(originx, originy);
    }
    let mul = 1;
    for (let i = 0; i < mul; i++) {
      particles = particles.concat(this.spawn(this.x, this.y));
    }
    if (particles.length > this.life * mul) {
      for (let i = 0; i < mul; i++) {
        particles.shift();
      }
    }
    this.particles = particles;
    this.render(this.canvas);
  }

  render() {
    let life = this.life;
    let ctx = this.canvas.getContext('2d');
    let particles = this.particles;
    for (let i = 0; i < particles.length; i++) {
      const p = particles[i];
      p.draw();
      p.emit(this.life);
      p.shrink();
    }
  }
}

// get the mouse position relative to the canvas
function getMousePos(cvs, evt) {
  const rect = cvs.getBoundingClientRect();
  return {
    x: evt.clientX - rect.left,
    y: evt.clientY - rect.top
  };
}

// animate the canvas
function animateCanvas(canvas, callback) {
  const ctx = canvas.getContext('2d');
  ctx.clearRect(0, 0, canvas.width, canvas.height);
  callback();
  requestAnimationFrame(animateCanvas.bind(null, canvas, callback));
}

// Update canvas size to fill window
function resizeCanvas(canvas) {
  canvas.width = window.innerWidth*devicePixelRatio;
  canvas.height = window.innerHeight*devicePixelRatio;
  // 动画默认出现在画布中心
  originx = canvas.width / 2;
  originy = canvas.height / 2;
}

onMounted(()=>{
    init();
})
</script>

<style scoped lang="scss">
.canvas {
  position: fixed;
  top: 0;
  left: 0;
  pointer-events: none;
}
</style>
