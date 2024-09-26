<template>
  <a-layout-header
    :class="{
      transition: istransition,
      head: true
    }"
    :style="animateStyle"
  >
    <router-link to="/home" class="logo" @click="handleClick">
      <h3>Loyal's Blog</h3>
    </router-link>
    <l-menu mode="horizontal" class="h-menu"></l-menu>
    <a-checkbox v-model:checked="$store.mouseEffect" style="color: #8dd0dd">
      鼠标特效
    </a-checkbox>
    <a-dropdown class="iconDiv" arrow>
      <a class="ant-dropdown-link" @click.prevent>
        <UnorderedListOutlined class="little-menu" />
      </a>
      <template #overlay>
        <l-menu class="d-Menu"></l-menu>
      </template>
    </a-dropdown>
  </a-layout-header>
  <LImg v-if="$route.path === '/home'" />
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { UnorderedListOutlined } from '@ant-design/icons-vue';
import LMenu from './L-Menu/index.vue';
import LImg from './L-Img/index.vue';
import { useStore } from '../../store';
const $store = useStore();
const $route = useRoute();
const $router = useRouter();
const istransition = ref(true);
const headerColor = getComputedStyle(document.documentElement)
  .getPropertyValue('--header-color')
  .trim();
const handleScroll = () => {
  const header = document.querySelector('header');
  const scrollY = window.scrollY;
  if (header) {
    if (scrollY < 300) {
      header.style.backgroundColor = 'transparent';
    } else {
      header.style.backgroundColor = headerColor;
    }
  }
};
const isHome = computed(() => $route.path === '/home');
const animateStyle = computed(() => {
  if (isHome.value) {
    window.addEventListener('scroll', handleScroll);
    istransition.value = true;
  } else {
    window.removeEventListener('scroll', handleScroll);
    istransition.value = false;
  }
  return isHome.value
    ? 'backgroundColor: transparent'
    : `backgroundColor: ${headerColor}`;
});
const handleClick = () => {
  window.scrollTo({ top: 0, behavior: 'smooth' });
  $router.push('/home'); // 动画完成后进行路由跳转
};
</script>

<style scoped lang="scss">
.ant-layout-header {
  display: flex;
  position: sticky;
  top: 0;
  z-index: 10;
  width: 100%;
  height: $header-height;
  background-color: transparent;
  align-items: center;
  padding: 0 !important;
  padding-left: 2.5rem !important;
  .logo h3 {
    display: block;
    width: $logo-width;
    color: $logo-color;
  }
  .iconDiv {
    margin-left: auto;
    box-sizing: border-box;
    height: inherit;
    padding: 0 1.5rem;
    display: none;
    color: $text-color;
    &:hover {
      color: $logo-color !important;
      background-color: $bg-color;
    }
  }
  .iconDiv .little-menu {
    color: inherit;
    vertical-align: text-top;
    font-size: $icon-size;
  }
}
.transition {
  transition: background-color 0.8s ease;
}

@media (max-width: $media-max-content) {
  .ant-layout-header {
    background-color: $header-color !important;
    transition: none;
  }
}

@media (max-width: $media-max-header) {
  .ant-menu {
    display: none;
  }
  .iconDiv {
    display: block !important;
  }
}
</style>
