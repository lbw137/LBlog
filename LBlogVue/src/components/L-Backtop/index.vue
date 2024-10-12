<template>
  <div
    class="l-backtop"
    @click="scrollToTop"
    :class="{ 'is-visible': showBacktop }"
  >
    <l-icon icon="icon-paper-airplane" scale="5"></l-icon>
  </div>
</template>

<script setup lang="ts">
import { onUnmounted, ref } from 'vue';

const showBacktop = ref(false);

const scrollToTop = () => {
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  });
};

const onScroll = () => {
  showBacktop.value = window.scrollY > 100;
};

// 监听滚动事件
window.addEventListener('scroll', onScroll);

// 组件卸载时移除事件监听器
onUnmounted(() => {
  window.removeEventListener('scroll', onScroll);
});
</script>

<style scoped lang="scss">
.l-backtop {
  position: fixed;
  bottom: 6%;
  right: 2%;

  cursor: pointer;
  opacity: 0; /* 默认不显示 */
  visibility: hidden; /* 默认不显示 */
  transition:
    opacity 0.3s ease-in-out,
    visibility 0.3s ease-in-out;
}

.l-backtop.is-visible {
  opacity: 1;
  visibility: visible;
}
</style>
