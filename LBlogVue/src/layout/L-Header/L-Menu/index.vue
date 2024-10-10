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
import { SubMenuType } from 'antd/es/menu/interface';
import { useRouter, useRoute } from 'vue-router';
import { ref, h, computed } from 'vue';
import { reqCategories } from '@/api/client/siteInfo';
const $route = useRoute();
const $router = useRouter();
const current = computed(() => [$route.path]);
const items = ref<MenuProps['items']>([]);
routes[0].children?.forEach(async (item) => {
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
  // 如果是分类，需要特殊处理
  if (item.name === 'category') {
    const res = await reqCategories();
    if (res.code === 200) {
      if (items.value && items.value.length > 1) {
        (items.value[1] as SubMenuType).children = res.data.categories.map(
          (i) => ({
            key: `/category/${i.title}?id=${i.id}&color=${i.color}`.replace(
              '#',
              '%23'
            ),
            label: i.title
          })
        );
      }
    }
  }
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
