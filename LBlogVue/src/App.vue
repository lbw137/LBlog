<template>
  <div>
    <router-view />
  </div>
</template>

<script setup lang="ts">
import { useMouseAnimate } from './hooks/useMouse.ts';

useMouseAnimate();
</script>

<style scoped lang="scss"></style>
