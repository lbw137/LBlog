<template>
  <div class="left">
    <a-card>
      <template #cover>
        <img alt="example" src="@/assets/image/roxy.jpg" />
      </template>
      <div class="area">
        <h1>Loyal</h1>
        <l-span>洛琪希，我的洛琪希，嘻嘻~~~</l-span>
      </div>
      <a-collapse
        v-model:activeKey="activeKey"
        accordion
        :bordered="false"
        expand-icon-position="end"
      >
        <a-collapse-panel key="1">
          <template #header>
            <a-space>
              <span>最喜欢的动漫</span>
              <l-icon icon="icon-TV---" scale="1.5"></l-icon>
            </a-space>
          </template>
          <p>无职转生</p>
        </a-collapse-panel>
        <a-collapse-panel key="2">
          <template #header>
            <a-space>
              <span>最喜欢我的女孩子们</span>
              <l-icon icon="icon--heart-with-arrow" scale="1.5"></l-icon>
            </a-space>
          </template>
          <p>洛琪希、伊蕾娜</p>
        </a-collapse-panel>
        <a-collapse-panel key="3">
          <template #header>
            <a-space>
              <span>最喜欢玩的游戏</span>
              <l-icon icon="icon-56-XboxOneControllerFlat" scale="1.5"></l-icon>
            </a-space>
          </template>
          <p>王者荣耀、英雄联盟</p>
        </a-collapse-panel>
      </a-collapse>
    </a-card>
    <a-card class="catalog" v-if="isDetail">
      <template #title>
        <ProfileOutlined style="margin-right: 1rem" />目录
      </template>
      <MdCatalog
        editorId="preview-only"
        :scrollElement="scrollElement"
        style="padding: 1%"
        :offsetTop="65"
        :scrollElementOffsetTop="60"
      />
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import { ProfileOutlined } from '@ant-design/icons-vue';
import { MdCatalog } from 'md-editor-v3';
import { useRoute } from 'vue-router';
import 'md-editor-v3/lib/preview.css';
const $route = useRoute();
const activeKey = ref(['0']);
const scrollElement = document.documentElement;
const isDetail = computed(() => {
  return $route.path.includes('detail');
});
</script>

<style scoped lang="scss">
.left {
  width: 20%;
  border-radius: 3%;
  align-self: stretch;
  h1 {
    margin-top: 0 !important;
    margin-bottom: 1rem;
  }
  .area {
    border-bottom: 1px solid #f2f2f2;
    padding-bottom: 0.8rem;
    text-align: center;
    border-radius: 3%;
  }
  .ant-collapse {
    background-color: #fff;
    font-size: 1.3rem;
    .ant-collapse-item {
      border-bottom: 1px solid #f2f2f2;
      border-radius: 3% !important;
      p {
        font-weight: 300;
      }
    }
  }
  .catalog {
    margin-top: 2rem;
    position: sticky;
    top: 8.5rem;
  }
}
</style>
