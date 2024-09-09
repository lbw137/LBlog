<template>
  <a-layout-header class="header">
    <a href="/" class="logo">
      <h3>LBW's Blog</h3>
    </a>
    <a-menu
      mode="horizontal"
      v-model:selectedKeys="current"
      :items="items"
      @click="handleClick"
    >
    </a-menu>
  </a-layout-header>
  <slot v-if="$route.path === '/home'" name="img"></slot>
</template>

<script setup lang="ts">
import { h, ref, computed, watch, onMounted } from 'vue';
import { MenuProps } from 'ant-design-vue';
import { useRouter, useRoute } from 'vue-router';
import { routes } from '../../router';
const $router = useRouter();
const $route = useRoute();
const current = ref<string[]>([$route.path]);
const items = ref<MenuProps['items']>([]);
routes[0].children?.forEach((i) => {
  items.value?.push({
    key: i.path,
    icon: () => h(i.meta?.icon as any),
    label: i.meta?.title,
    title: i.meta?.title as string
  });
});
const handleClick = ({ key }: { key: string }) => {
  $router.push(key);
};
const handleScroll = () => {
  const header = document.querySelector('header');
  const scrollY = window.scrollY;
  if (header) {
    if (scrollY < 300) {
      header.style.backgroundColor = 'transparent';
    } else {
      header.style.backgroundColor = '#1b1c1d';
    }
  }
};
const isHome = computed(() => $route.path === '/home');
onMounted(() => {
  const header = document.querySelector('header');
  if (header) {
    header.style.backgroundColor = isHome.value ? 'transparent' : '#1b1c1d';
  }
});

watch(isHome, (newValue) => {
  const header = document.querySelector('header');
  if (header) {
    if (newValue) window.addEventListener('scroll', handleScroll);
    else window.removeEventListener('scroll', handleScroll);
    header.style.backgroundColor = newValue ? 'transparent' : '#1b1c1d';
  }
});
</script>

<style scoped lang="scss">
.ant-layout-header {
  display: flex;
  position: sticky;
  top: 0;
  z-index: 1;
  width: 100%;
  height: 64px;
  background-color: transparent;
  transition: background-color 0.8s ease;
}
.home {
  background-color: #1b1c1d;
}
.logo {
  display: block;
  width: 100px;
  color: #38dbfb;
}
.ant-menu {
  line-height: 64px;
  background-color: inherit;
  color: #8dd0dd;
  :deep(.ant-menu-item) {
    &::after {
      border: 0 !important;
    }
    &:hover {
      color: #38dbfb !important;
      background-color: rgba($color: #fff, $alpha: 0.15);
    }
  }
  :deep(.ant-menu-item-selected) {
    color: #38dbfb !important;
    background-color: rgba($color: #fff, $alpha: 0.15);
  }
}
</style>
