<template>
  <a-config-provider :locale="locale">
    <div>
      <router-view v-slot="{ Component }">
        <component :is="Component" />
      </router-view>
      <l-mouse v-if="$store.mouseEffect"></l-mouse>
    </div>
  </a-config-provider>
</template>

<script setup lang="ts">
import zhCN from 'ant-design-vue/es/locale/zh_CN';
import dayjs from 'dayjs';
import 'dayjs/locale/zh-cn';
import { useStore } from './store';
import { useSiteInfo } from './store/useSiteInfo';
import { config } from 'md-editor-v3';
import LinkAttr from 'markdown-it-link-attributes';

const $site = useSiteInfo();
const $store = useStore();
dayjs.locale('zh-cn');
const locale = zhCN;

$site.getSiteInfo();

config({
  markdownItPlugins(plugins) {
    plugins = plugins.map((item) => {
      if (item.type === 'taskList') {
        return {
          ...item,
          options: {
            ...item.options,
            enabled: true
            // 如果只是想对某个编辑器开启这个功能
            // enabled: editorId === 'myId'
          }
        };
      }
      return item;
    });
    return [
      ...plugins,
      {
        type: 'linkAttr',
        plugin: LinkAttr,
        options: {
          matcher(href: string) {
            // 如果使用了markdown-it-anchor
            // 应该忽略标题头部的锚点链接
            return !href.startsWith('#');
          },
          attrs: {
            target: '_blank'
          }
        }
      }
    ];
  }
});
</script>

<style scoped lang="scss"></style>
