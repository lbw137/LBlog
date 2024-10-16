<template>
  <a-menu
    :mode="mode"
    @click="onClick"
    :class="class"
    :items="items"
    v-model:selectedKeys="current"
  >
  </a-menu>
</template>

<script setup lang="ts">
import { MenuProps } from 'ant-design-vue';
import { useRouter, useRoute } from 'vue-router';
import { computed } from 'vue';
import { useSiteInfo } from '@/store/useSiteInfo';
import { storeToRefs } from 'pinia';
const $route = useRoute();
const $router = useRouter();
const current = computed(() => [$route.path]);
const $site = useSiteInfo();
const items = storeToRefs($site).menuInfo;
defineProps({
  mode: {
    type: String as () => 'horizontal' | 'vertical',
    default: 'vertical'
  },
  class: {
    type: String
  }
});
const onClick: MenuProps['onClick'] = ({ key }) => {
  // 如果点击的是当前路由，则不跳转，否则跳转到对应的路由
  if (current.value.includes(key as string)) return;
  $router.push((key as string).trim());
};
</script>

<style scoped lang="scss">
.d-Menu {
  background-color: $header-color !important;
  width: $d-menu-width;
}

.h-menu {
  width: $h-menu-width;
  line-height: $header-height;
  background-color: transparent;
  color: $text-color;
  transition: background-color 0.8s ease;
}
</style>
