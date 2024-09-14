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
        header.style.backgroundColor = newValue ? 'transparent' : '#1b1c1d';
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
  height: 6.4rem;
  background-color: transparent;
  align-items: center;
  padding: 0 !important;
  padding-left: 2% !important;
  .logo h3 {
    display: block;
    min-width: 12rem;
    color: #38dbfb;
  }
  .iconDiv {
    margin-left: auto;
    box-sizing: border-box;
    height: inherit;
    padding: 0 1.5rem;
    display: none;
    &:hover {
      color: #38dbfb !important;
      background-color: rgba($color: #fff, $alpha: 0.15);
    }
  }
  .iconDiv .icon {
    color: #8dd0dd;
    vertical-align: text-top;
    font-size: 2.2rem;
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

@media (max-width: 767px) {
  .ant-layout-header {
    background-color: #1b1c1d !important;
    transition: none;
  }
  .ant-menu {
    display: none;
  }
  .iconDiv {
    display: block !important;
  }
  .imgDiv {
    display: none !important;
  }
}
</style>
