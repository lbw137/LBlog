<template>
  <a-config-provider :locale="locale">
    <div>
      <router-view v-slot="{ Component }">
        <keep-alive>
          <component :is="Component" />
        </keep-alive>
      </router-view>
      <l-mouse v-if="$store.mouseEffect"></l-mouse>
    </div>
  </a-config-provider>
</template>

<script setup lang="ts">
import zhCN from 'ant-design-vue/es/locale/zh_CN';
import dayjs from 'dayjs';
import 'dayjs/locale/zh-cn';
import { useStore } from './store';
const $store = useStore();
dayjs.locale('zh-cn');
const locale = zhCN;
</script>

<style scoped lang="scss"></style>
