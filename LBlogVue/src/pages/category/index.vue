<template>
  <a-card>
    <l-icon icon="icon-sakura-flower"></l-icon>
    分类
    <span :style="color">{{ $route.meta.title }}</span>
    下的文章
    <l-icon icon="icon-sakura-flower"></l-icon>
  </a-card>
  <l-article></l-article>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useRoute } from 'vue-router';
const $route = useRoute();
const color = computed(() => `color: ${$route.meta.color};`);
</script>

<style scoped lang="scss">
.ant-card {
  margin-bottom: 1rem;
  font-size: 2.4rem;
  text-align: center;
  padding: 1rem 0;
}
</style>
