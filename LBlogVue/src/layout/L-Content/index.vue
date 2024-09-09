<template>
  <a-layout-content class="content">
    <div
      :style="{
        background: 'transparent',
        padding: '24px',
        minHeight: '280px'
      }"
    >
      <router-view></router-view>
    </div>
  </a-layout-content>
</template>

<script setup lang="ts">
import { useRoute } from 'vue-router';
import { watch, computed, onMounted } from 'vue';
const $route = useRoute();
const isHome = computed(() => $route.path === '/home');

onMounted(() => {
  const content: HTMLBaseElement | null = document.querySelector('.content');
  if (content) {
    content.style.marginTop = isHome.value ? 'calc(100vh - 64px)' : '0';
  }
});

watch(isHome, (newValue) => {
  const content: HTMLBaseElement | null = document.querySelector('.content');
  if (content) {
    content.style.marginTop = newValue ? 'calc(100vh - 64px)' : '0';
  }
});
</script>

<style scoped lang="scss">
.content {
  padding: 30px 50px;
  background: url('../../assets/image/bg1.png') center fixed no-repeat;
  background-size: cover;
}
</style>
