<template>
  <div class="l-btn">
    <slot></slot>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss">
$height: 4rem;
.l-btn {
  display: inline-block;
  background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  background-size: 400%;
  color: #fff;
  line-height: $height;
  height: $height;
  border-radius: $height;
  padding: 0 2rem;
  cursor: pointer;
  &:hover {
    animation: animate 7s linear infinite;
    &::after {
      filter: blur(1rem);
      opacity: 0.4;
      animation: animate 7s linear infinite;
    }
  }
}

.l-btn::after {
  content: '';
  display: block;
  background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  background-size: 400%;
  height: inherit;
  opacity: 0;
  transform: translateY(-$height) scale(1.1) scaleX(1.5);
  pointer-events: none;
}

@keyframes animate {
  from {
    background-position: 0%;
  }

  to {
    background-position: 400%;
  }
}
</style>
