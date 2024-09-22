<template>
  <a-card>
    <div class="header">
      <h1>我的动态</h1>
    </div>
    <a-list
      item-layout="vertical"
      size="large"
      :pagination="pagination"
      :data-source="listData"
    >
      <template #renderItem="{ item }">
        <a-list-item key="item.title">
          <template #actions>
            <span v-for="{ icon, text } in actions" :key="icon">
              <component :is="icon" style="margin-right: 8px" />
              {{ text }}
            </span>
          </template>
          <a-list-item-meta :title="item.name" :description="item.date">
            <template #avatar
              ><a-avatar :src="item.avatar" size="large"
            /></template>
          </a-list-item-meta>
          {{ item.content }}
        </a-list-item>
      </template>
    </a-list>
  </a-card>
</template>
<script lang="ts" setup>
import { LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
const listData: Record<string, string>[] = [];
listData.push({
  name: 'Roxy',
  date: '2024-08-20 01:54',
  avatar: '/src/assets/image/roxy.jpg',
  content:
    "欢迎来到 Loyal's Blog，一个致力于分享知识、见解和生活点滴的数字空间。在这里，您将遇见 Loyal，一位对技术充满热情、对写作怀有深情的博主。Loyal 相信，通过文字，我们不仅能够记录生活，还能够启发思考，连接彼此。Loyal's Blog 涵盖了一系列主题，从最新科技趋势、编程技巧到个人成长和生活哲学，每一篇文章都是 Loyal 对世界的独到见解和真诚分享。无论您是技术爱好者、终身学习者，还是寻找生活灵感的旅人，这里都有您感兴趣的内容。我们承诺，每一篇文章都将用心去撰写，每一次更新都将带来价值。加入我们，一起探索知识的海洋，体验成长的喜悦，感受生活的美好。感谢您的访问，期待与您在字里行间相遇。"
});
for (let i = 0; i < 23; i++) {
  listData.push({
    name: 'Roxy',
    date: '2024-08-20 01:54',
    avatar: '/src/assets/image/roxy.jpg',
    content: '博客项目，启动！！！'
  });
}
const pagination = {
  onChange: (page: number) => {
    console.log(page);
  },
  pageSize: 3
};
const actions: Record<string, any>[] = [
  { icon: LikeOutlined, text: '156' },
  { icon: MessageOutlined, text: '2' }
];
</script>

<style lang="scss">
.header {
  text-align: center;
  padding: 1rem 0;
  border-bottom: 2px solid #dededf;
}
.ant-list-pagination {
  text-align: center !important;
  margin-bottom: 2rem;
}
</style>
