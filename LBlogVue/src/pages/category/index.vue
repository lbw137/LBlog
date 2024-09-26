<template>
  <a-card>
    <l-icon icon="icon-sakura-flower"></l-icon>
    分类
    <span :style="color">{{ $route.meta.title }}</span>
    下的文章
    <l-icon icon="icon-sakura-flower"></l-icon>
  </a-card>
  <l-article :data="data"></l-article>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useRoute } from 'vue-router';
const $route = useRoute();
const color = computed(() => `color: ${$route.meta.color};`);
const data = [
  {
    title: 'Vue3知识点123123123123123',
    date: '2024-09-19',
    views: 2333,
    letters: '1068',
    time: '5',
    img: '/src/assets/image/bg.jpg',
    category: '分类列表',
    tags: [
      ['666', '#38dbfb'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae']
    ]
  },
  {
    title: 'Vue3知识点',
    date: '2024-09-19',
    views: 2333,
    letters: '1068',
    time: '5',
    img: '/src/assets/image/bg.jpg',
    category: '分类列表',
    tags: [
      ['666', '#38dbfb'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae']
    ]
  },
  {
    title: 'Vue3知识点',
    date: '2024-09-19',
    views: 2333,
    letters: '1068',
    time: '5',
    img: '/src/assets/image/bg.jpg',
    category: '分类列表',
    tags: [
      ['666', '#38dbfb'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae']
    ]
  },
  {
    title: 'Vue3知识点',
    date: '2024-09-19',
    views: 2333,
    letters: '1068',
    time: '5',
    img: '/src/assets/image/bg.jpg',
    category: '分类列表',
    tags: [
      ['666', '#38dbfb'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae']
    ]
  }
];
</script>

<style scoped lang="scss">
.ant-card {
  margin-bottom: 1rem;
  font-size: 2.4rem;
  text-align: center;
  padding: 1rem 0;
}
</style>
