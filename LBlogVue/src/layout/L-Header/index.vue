<template>
  <a-layout-header
    :class="{
      transition: istransition,
      head: true
    }"
  >
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
    <a-dropdown
      class="iconDiv"
      arrow
      :overlayStyle="{
        backgroundColor: 'red'
      }"
    >
      <a class="ant-dropdown-link" @click.prevent>
        <UnorderedListOutlined class="icon" />
      </a>
      <template #overlay>
        <a-menu @click="onClick" class="dropMenu">
          <a-menu-item key="/home">首页</a-menu-item>
          <a-menu-item key="/category">分类</a-menu-item>
          <a-menu-item key="/archivist">归档</a-menu-item>
          <a-menu-item key="/dynamic">动态</a-menu-item>
          <a-menu-item key="/friends">友链</a-menu-item>
          <a-menu-item key="/about">关于我</a-menu-item>
        </a-menu>
      </template>
    </a-dropdown>
  </a-layout-header>
  <div class="imgDiv">
    <slot v-if="$route.path === '/home'" name="img"></slot>
  </div>
</template>

<script setup lang="ts">
import { h, ref, computed, watch, nextTick } from 'vue';
import { MenuProps } from 'ant-design-vue';
import { useRouter, useRoute } from 'vue-router';
import { routes } from '../../router';
import { UnorderedListOutlined } from '@ant-design/icons-vue';
const $router = useRouter();
const $route = useRoute();
const current = ref<string[]>([$route.path]);
const items = ref<MenuProps['items']>([]);
const istransition = ref(true);
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

const onClick: MenuProps['onClick'] = ({ key }) => {
  $router.push(key as string);
};
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
  padding-left: 5rem !important;
  .logo h3 {
    display: block;
    min-width: 10rem;
    color: #38dbfb;
  }
  .ant-menu {
    width: 51.8rem;
    line-height: 6.4rem;
    background-color: transparent;
    color: #8dd0dd;
    transition: background-color 0.8s ease;
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
  .iconDiv {
    margin-left: auto;
    box-sizing: border-box;
    height: inherit;
    padding: 0 1.5rem;
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

.dropMenu,
.ant-dropdown-arrow::before {
  background-color: green !important;
}

@media (min-width: 768px) {
  .ant-menu {
    display: block !important;
  }
  .iconDiv {
    display: none !important;
  }
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
