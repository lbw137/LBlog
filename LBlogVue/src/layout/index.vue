<template>
  <a-layout class="layout">
    <!-- 头部区域 -->
    <LHeader>
      <!-- 首页背景图 -->
      <template #img>
        <LImg />
      </template>
    </LHeader>
    <!-- 内容区域 -->
    <LContent></LContent>
    <!-- 底部区域 -->
    <LFooter></LFooter>
  </a-layout>
</template>

<script setup lang="ts">
import LHeader from './L-Header/index.vue';
import LContent from './L-Content/index.vue';
import LFooter from './L-Footer/index.vue';
import LImg from './L-Img/index.vue';
</script>

<style scoped lang="scss"></style>
