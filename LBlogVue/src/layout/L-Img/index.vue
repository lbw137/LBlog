<template>
  <div class="bigImg">
    <div class="img1 img"></div>
    <div class="img2 img"></div>
    <div class="img3 img"></div>
  </div>
  <div class="wave1"></div>
  <div class="wave2"></div>
</template>

<script setup lang="ts">
import { computed, watch, nextTick } from 'vue';
import { useRoute } from 'vue-router';
const $route = useRoute();
const isHome = computed(() => $route.path === '/home');
const imgAnimation = () => {
  const bigImg: HTMLElement | null = document.querySelector('.bigImg');
  let preX: number | null = null;
  if (bigImg) {
    bigImg.addEventListener('mousemove', (e: MouseEvent) => {
      const width = bigImg.offsetWidth;
      const X = e.offsetX;
      const percentage = Number(
        getComputedStyle(bigImg).getPropertyValue('--percentage').trim()
      );
      let newPer: number;
      if (preX !== null) {
        const dif = preX - X;
        let change = (2 / width) * dif;
        newPer = percentage - change; // 往右走时要变大，左走相反
        bigImg.style.setProperty('--percentage', String(newPer));
      }
      preX = X;
    });

    bigImg.addEventListener('mouseleave', () => {
      bigImg.style.setProperty('--percentage', '1');
      preX = null;
    });
  }
};

watch(
  isHome,
  (newValue) => {
    if (newValue) {
      //等待渲染完成执行动画函数
      nextTick(imgAnimation);
    }
  },
  {
    immediate: true
  }
);
</script>

<style scoped>
.bigImg {
  position: absolute;
  display: flex;
  justify-content: center;
  width: 100%;
  height: 100vh;
  --percentage: 1;
  transform: translateX(calc((var(--percentage) - 1) * 2.5rem));
  .img {
    position: absolute;
    width: 110%;
    height: inherit;
    transition: opacity 0.3s ease;
  }
  .img1 {
    background: url('@/assets/image/main.png') center no-repeat;
    background-size: cover;
    opacity: var(--percentage);
    z-index: 1;
  }
  .img2 {
    background: url('@/assets/image/left.jpg') center no-repeat;
    background-size: cover;
    opacity: calc(var(--percentage) - 1);
    z-index: 2;
  }
  .img3 {
    background: url('@/assets/image/right.jpg') center no-repeat;
    background-size: cover;
  }
}
.wave1 {
  background: url('@/assets/image/wave1.png') repeat-x;
  height: 7.5rem;
  width: 100%;
  position: absolute;
  bottom: 0;
  opacity: 0.5;
  z-index: 3;
}
.wave2 {
  background: url('@/assets/image/wave2.png') repeat-x;
  height: 9rem;
  width: calc(100% + 10rem);
  left: -10rem;
  position: absolute;
  bottom: 0;
  opacity: 0.8;
  z-index: 3;
}
</style>
