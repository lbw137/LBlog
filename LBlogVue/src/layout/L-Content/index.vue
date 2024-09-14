<template>
  <a-layout-content class="content">
    <div class="conDiv">
      <div class="left">123</div>
      <div class="route">
        <router-view></router-view>
      </div>
      <div class="right">321</div>
    </div>
  </a-layout-content>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router';
import { watch, computed, nextTick } from 'vue';
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
  padding-top: 2%;
  display: flex;
  justify-content: space-around;
  .left {
    width: 15%;
    height: 100vh;
  }
  .right {
    width: 15%;
    height: 100vh;
  }
}

@media (min-width: 768px) {
  .route {
    width: 60%;
  }
  .left,
  .right {
    display: block;
  }
}

@media (max-width: 767px) {
  .content {
    margin-top: 0 !important;
  }
  .left,
  .right {
    display: none;
  }
  .route {
    width: 95%;
  }
}
</style>
