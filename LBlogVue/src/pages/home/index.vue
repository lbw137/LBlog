<template>
  <l-article :data="data"></l-article>
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
import request from '@/utils/requestAdmin';
const data = [
  {
    id: 1,
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
    id: 2,
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
    id: 3,
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
    id: 4,
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
onMounted(() => {
  setTimeout(() => {
    request({
      url: '/user/test',
      method: 'get'
    });
  }, 1000 * 6);
});
</script>

<style scoped></style>
