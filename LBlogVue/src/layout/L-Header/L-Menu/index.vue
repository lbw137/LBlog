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
import { routes } from '@/router';
import { MenuProps } from 'ant-design-vue';
import { useRouter, useRoute } from 'vue-router';
import { ref, h, computed } from 'vue';
const $route = useRoute();
const $router = useRouter();
const current = computed(() => [$route.path]);
const items = ref<MenuProps['items']>([]);
routes[0].children?.forEach((item) => {
  if (item.meta?.isHidden) return;
  items.value?.push({
    key: item.path,
    label: item.meta?.title,
    icon: () => h(item.meta?.icon as any) as any,
    children: item.children?.map((i) => ({
      key: i.path,
      label: i.meta?.title
    }))
  });
});
items.value?.push({
  key: routes[1].path,
  label: routes[1].meta?.title,
  icon: () => h(routes[1].meta?.icon as any) as any,
  children: routes[1].children?.map((i) => ({
    key: i.path,
    label: i.meta?.title
  }))
});
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
