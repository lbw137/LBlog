<template>
  <div
    class="box"
    :style="{
      backgroundColor: color
    }"
  >
    {{ text }}
  </div>
</template>

<script setup lang="ts">
defineProps({
  text: {
    type: String
  },
  color: {
    type: String
  }
});
</script>

<style scoped lang="scss">
$length: 2.5rem;
$arrow-length: calc($length * sqrt(2) / 2);
.box {
  position: relative;
  height: $length;
  box-sizing: border-box;
  padding: 0 calc($length/2);
  line-height: $length;
  text-wrap: nowrap;
  font-size: 1.4rem;
  color: #fff;
  border-radius: 0 0.4rem 0.4rem 0;
  cursor: pointer;
  &:hover {
    box-shadow: $tag-shadow;
  }
  &::before {
    content: '';
    background-color: inherit;
    width: $arrow-length;
    height: $arrow-length;
    position: absolute;
    transform-origin: top left;
    transform: translateX(calc(-1 * $length/2)) rotate(45deg);
    // 天才inherit
    box-shadow: inherit;
  }
  &::after {
    content: '';
    background-color: #fff;
    width: 0.5rem;
    height: 0.5rem;
    border-radius: 50%;
    position: absolute;
    left: 0;
    margin-top: calc($length/2);
    transform: translate(-0.25rem, -0.25rem);
  }
}
</style>
