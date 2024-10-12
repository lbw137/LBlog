<template>
  <a-card>
    <div class="header">
      <h1>文章归档</h1>
      <span>好！目前共计{{ data?.count }}篇博客，继续努力。</span>
    </div>
    <l-timeline :data="data?.blogMap" @click="onclick"></l-timeline>
  </a-card>
</template>

<script setup lang="ts">
import { useSiteInfo } from '@/store/useSiteInfo';
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';
const $site = useSiteInfo();
const $router = useRouter();
const data = storeToRefs($site).blogsArcInfo;
if (!data.value) {
  $site.getBlogsArcInfo();
}
const onclick = (id: number) => {
  $router.push('/detail/' + id);
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
