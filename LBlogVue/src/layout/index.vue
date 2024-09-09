<template>
  <a-layout class="layout">
    <!-- 头部区域 -->
    <LHeader>
      <!-- 首页背景图 -->
      <template #img>
        <div class="img"></div>
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
</script>

<style scoped lang="scss">
.img {
  position: absolute;
  background: url('../assets/image/bg5.png') center no-repeat;
  background-size: cover;
  width: 100%;
  height: 100vh;
}
</style>
