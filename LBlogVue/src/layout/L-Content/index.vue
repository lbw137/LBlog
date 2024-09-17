<template>
  <a-layout-content class="content">
    <div class="conDiv">
      <div class="left">
        <a-card hoverable style="width: 100%">
          <template #cover>
            <img
              alt="example"
              src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
            />
          </template>
          <template #actions>
            <setting-outlined key="setting" />
            <edit-outlined key="edit" />
            <ellipsis-outlined key="ellipsis" />
          </template>
          <a-card-meta title="Card title" description="This is the description">
            <template #avatar>
              <a-avatar src="https://joeschmoe.io/api/v1/random" />
            </template>
          </a-card-meta>
        </a-card>
      </div>
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
    width: 20%;
    height: 100vh;
  }
  .right {
    width: 15%;
    height: 100vh;
  }
  .route {
    width: 60%;
  }
}

@media (max-width: $media-max) {
  .content {
    margin-top: 0 !important;
  }
  .left,
  .right {
    display: none;
  }
  .route {
    width: 95% !important;
  }
}
</style>
