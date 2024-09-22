<template>
  <a-card>
    <div class="header">
      <h1>文章归档</h1>
      <span>好！目前共计{{ data.count }}篇博客，继续努力。</span>
    </div>
    <l-timeline :data="data.blogMap"></l-timeline>
  </a-card>
</template>

<script setup lang="ts">
const data = {
  count: 5,
  blogMap: [
    {
      date: '2024年9月',
      blogs: [
        { day: '22日', title: 'Vue3知识点' },
        { day: '10日', title: '博客项目，启动启动启动启动启动启动' }
      ]
    },
    {
      date: '2024年9月',
      blogs: [
        { day: '22日', title: 'Vue3知识点' },
        { day: '10日', title: '博客项目，启动' }
      ]
    },
    {
      date: '2024年9月',
      blogs: [
        { day: '22日', title: 'Vue3知识点' },
        { day: '10日', title: '博客项目，启动' }
      ]
    },
    {
      date: '2024年9月',
      blogs: [
        { day: '22日', title: 'Vue3知识点' },
        { day: '10日', title: '博客项目，启动' }
      ]
    },
    {
      date: '2024年9月',
      blogs: [
        { day: '22日', title: 'Vue3知识点' },
        { day: '10日', title: '博客项目，启动' }
      ]
    },
    {
      date: '2024年9月',
      blogs: [
        { day: '22日', title: 'Vue3知识点' },
        { day: '10日', title: '博客项目，启动' }
      ]
    }
  ]
};
</script>

<style scoped lang="scss">
.header {
  text-align: center;
  padding-bottom: 1rem;
  border-bottom: 2px solid #dededf;
  h1 {
    margin-bottom: 1rem;
  }
}
</style>
