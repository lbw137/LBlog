<template>
  <span v-for="(item, index) in contentArray" :style="'--i:' + index">
    {{ item }}
  </span>
</template>

<script setup lang="ts">
import { useSlots } from 'vue';
const $slots = useSlots();
const slot = $slots.default ? $slots.default() : null;
const content = slot ? slot[0].children : null;
const contentArray = typeof content === 'string' ? content.split('') : [];
console.log(contentArray);
</script>

<style scoped lang="scss">
span {
  color: transparent;
  animation: animate 3s linear infinite;
  animation-delay: calc(0.1s * var(--i));
}
@keyframes animate {
  0% {
    color: $logo-color;
    filter: hue-rotate(0deg);
    text-shadow: 0 0 10px #22ffcc, 0 0 20px #22ffcc, 0 0 40px #22ffcc,
      0 0 80px #22ffcc;
  }
  30%,
  70% {
    color: $logo-color;
    filter: hue-rotate(360deg);
    text-shadow: 0 0 10px #22ffcc, 0 0 20px #22ffcc, 0 0 40px #22ffcc;
  }
  100% {
    color: transparent;
    filter: hue-rotate(0deg);
  }
}
</style>
