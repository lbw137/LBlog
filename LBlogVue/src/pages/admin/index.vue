<template>
  <a-layout class="layout">
    <!-- 头部区域 -->
    <LHeader> </LHeader>
    <!-- 内容区域 -->
    <div>
      <a-menu v-model:selectedKeys="current" mode="horizontal" @click="onClick">
        <a-menu-item key="list"> 列表 </a-menu-item>
        <a-menu-item key="pub"> 发布 </a-menu-item>
      </a-menu>
      <router-view></router-view>
    </div>
    <!-- 底部区域 -->
    <LFooter></LFooter>
  </a-layout>
</template>

<script setup lang="ts">
import LHeader from '@/layout/L-Header/index.vue';
import LFooter from '@/layout/L-Footer/index.vue';
import { MenuProps } from 'ant-design-vue';
import { computed } from 'vue';
import { useRouter, useRoute } from 'vue-router';
const $router = useRouter();
const $route = useRoute();
const path = computed(() => {
  return $route.path;
});
const current = computed(() => {
  return [path.value.split('/')[3]];
});
const onClick: MenuProps['onClick'] = ({ key }) => {
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  });
  $router.push((key as string).trim());
};
</script>

<style scoped lang="scss">
.ant-menu {
  display: flex;
  justify-content: flex-end;
  padding-right: 10%;
  border-bottom: 1px solid #ccc;
  :deep(.ant-menu-item) {
    &:hover {
      color: $logo-color !important;
      background-color: #dcd9d9 !important;
    }
  }
  :deep(.ant-menu-item-selected) {
    color: $logo-color !important;
    background-color: #dcd9d9 !important;
  }
}
</style>
