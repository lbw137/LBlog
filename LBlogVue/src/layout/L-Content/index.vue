<template>
  <a-layout-content class="content">
    <div class="conDiv">
      <LLeft class="left"></LLeft>
      <div class="route">
        <router-view></router-view>
      </div>
      <LRight class="right"></LRight>
    </div>
  </a-layout-content>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router';
import { watch, computed, nextTick } from 'vue';
import LLeft from './L-Left/index.vue';
import LRight from './L-Right/index.vue';
const $route = useRoute();
const isHome = computed(() => $route.path === '/home');
watch(
  isHome,
  (newValue) => {
    nextTick(() => {
      const content: HTMLBaseElement | null =
        document.querySelector('.content');
      if (content) {
        content.style.marginTop = newValue ? 'calc(100vh - 6.4rem)' : '0';
      }
    });
  },
  {
    immediate: true
  }
);
</script>

<style scoped lang="scss">
.content {
  background: url('../../assets/image/bg.png') center fixed no-repeat;
  background-size: cover;
}

.conDiv {
  padding: 2% 0;
  display: flex;
  justify-content: space-around;
  align-items: flex-start;
  .route {
    width: 50%;
    min-width: $minWidth;
  }
}

@media (max-width: $media-max-content) {
  .content {
    margin-top: 0 !important;
  }
  .left,
  .right {
    display: none;
  }
  .route {
    width: 90% !important;
  }
}
</style>
