<template>
  <l-article :data="data"></l-article>
</template>

<script setup lang="ts">
import { useSiteInfo } from '@/store/useSiteInfo';
import { storeToRefs } from 'pinia';
const $site = useSiteInfo();
const data = storeToRefs($site).blogsInfo;
</script>

<style scoped></style>
