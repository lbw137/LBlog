<template>
  <a-layout-content class="content" :style="mgTop">
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
import { computed } from 'vue';
import LLeft from './L-Left/index.vue';
import LRight from './L-Right/index.vue';
const $route = useRoute();
const mgTop = computed(() =>
  $route.path === '/home'
    ? 'margin-top: calc(100vh - 6.4rem);'
    : 'margin-top: 0'
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
@media (max-width: $media-max-header) {
  .conDiv {
    padding: 0 !important;
    .route {
      width: 100% !important;
      :deep(.ant-card) {
        border-radius: 0% !important;
      }
    }
  }
  .content {
    background: #efefef;
  }
}
</style>
