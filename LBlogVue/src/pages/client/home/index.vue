<template>
  <l-article :data="data"></l-article>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { reqHomeBlogs } from '@/api/client/blog';
import type { BlogList } from '@/api/client/blog/type';
const data = ref<BlogList[]>([]);
onMounted(async () => {
  const res = await reqHomeBlogs();
  if (res.code === 200) {
    data.value = res.data.blogs;
  }
});
</script>

<style scoped></style>
