<template>
  <a-card>
    <l-icon icon="icon-sakura-flower" :scale="3"></l-icon>
    分类
    <span :style="color">{{ $route.params.title }}</span>
    下的文章
    <l-icon icon="icon-sakura-flower" :scale="3"></l-icon>
  </a-card>
  <l-article :data="data"></l-article>
</template>

<script setup lang="ts">
import { reqCatBlogs } from '@/api/client/blog';
import { BlogList } from '@/api/client/blog/type';
import { computed, ref, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
const $route = useRoute();
const color = computed(() => `color: ${$route.query.color};`);
const data = ref<BlogList[]>([]);
const getData = async () => {
  const res = await reqCatBlogs(Number($route.query.id));
  if (res.code === 200) data.value = res.data.blogs;
};
watchEffect(() => {
  if ($route.query.id) getData();
});
</script>

<style scoped lang="scss">
.ant-card {
  margin-bottom: 1rem;
  font-size: 2.4rem;
  text-align: center;
  padding: 1rem 0;
}
</style>
