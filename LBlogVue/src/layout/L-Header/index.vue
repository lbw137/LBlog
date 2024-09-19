<template>
  <a-layout-header
    :class="{
      transition: istransition,
      head: true
    }"
  >
    <a href="/" class="logo">
      <h3>Loyal's Blog</h3>
    </a>
    <LMenu mode="horizontal" class="h-menu"></LMenu>
    <a-dropdown class="iconDiv" arrow>
      <a class="ant-dropdown-link" @click.prevent>
        <UnorderedListOutlined class="icon" />
      </a>
      <template #overlay>
        <LMenu class="d-Menu"></LMenu>
      </template>
    </a-dropdown>
  </a-layout-header>
  <div class="imgDiv" v-if="$route.path === '/home'">
    <slot name="img"></slot>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, watch, nextTick } from 'vue';
import { useRoute } from 'vue-router';
import { UnorderedListOutlined } from '@ant-design/icons-vue';
import LMenu from '../L-Menu/index.vue';
const $route = useRoute();
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
watch(
  isHome,
  (newValue) => {
    nextTick(() => {
      const header = document.querySelector('header');
      if (newValue) {
        window.addEventListener('scroll', handleScroll);
        istransition.value = true;
      } else {
        window.removeEventListener('scroll', handleScroll);
        istransition.value = false;
      }
      if (header) {
        header.style.backgroundColor = newValue ? 'transparent' : headerColor;
      }
    });
  },
  {
    immediate: true
  }
);
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
  padding-left: 2% !important;
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
  .iconDiv .icon {
    color: inherit;
    vertical-align: text-top;
    font-size: $icon-size;
  }
}
.transition {
  transition: background-color 0.8s ease;
}

.imgDiv {
  position: absolute;
  justify-content: center;
  width: 100%;
  height: 100vh;
}

@media (max-width: $media-max-content) {
  .ant-layout-header {
    background-color: $header-color !important;
    transition: none;
  }
  .imgDiv {
    display: none !important;
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
