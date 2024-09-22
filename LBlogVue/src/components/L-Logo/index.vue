<template>
  <h1>Loyal's Blog</h1>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss">
h1 {
  position: relative;
  z-index: 5;
  color: #0e3742;
  letter-spacing: 1.5rem;
  text-transform: uppercase;
  line-height: 0.7em;
  font-family: arial;
  font-size: 8rem;
  text-wrap: nowrap;
  pointer-events: none;
  -webkit-box-reflect: below 0.1rem linear-gradient(transparent, #0008);
  transform-origin: top left;
  animation: animate 4s linear infinite, bounceInLeft 1s, hinge 2s linear 1s,
    zoomInDown 1s linear 3s, move 4s linear 4s infinite alternate;
}
// ,
@keyframes animate {
  0%,
  50%,
  100% {
    color: $logo-color;
  }
  25%,
  75% {
    color: #fff;
    text-shadow: 0 0 1rem #03bcf4, 0 0 2rem #03bcf4, 0 0 4rem #03bcf4,
      0 0 8rem #03bcf4;
  }
}
@keyframes move {
  50% {
    transform: translateY(-50%);
  }
  100% {
    transform: translateY(50%);
  }
}
</style>
