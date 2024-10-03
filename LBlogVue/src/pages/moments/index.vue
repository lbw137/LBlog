<template>
  <a-card>
    <div class="header">
      <h1>我的动态</h1>
    </div>
    <a-list
      item-layout="vertical"
      :pagination="pagination"
      :data-source="listData"
    >
      <template #renderItem="{ item }">
        <a-list-item key="item.date">
          <!-- 点赞评论 -->
          <template #actions>
            <span
              :class="{
                action: true,
                active: item.isLike
              }"
              @click="handleLike(item)"
            >
              <LikeOutlined style="margin-right: 0.5rem" />
              {{ item.likes }}
            </span>
            <span class="action" @click="handleComement(item)">
              <message-outlined style="margin-right: 0.5rem" />
              {{ item.comments }}
            </span>
          </template>
          <!-- 头像、昵称、日期 -->
          <a-list-item-meta :title="item.name" :description="item.date">
            <template #avatar>
              <a-avatar :src="item.avatar" size="large" />
            </template>
          </a-list-item-meta>
          <!-- 内容 -->
          {{ item.content }}
        </a-list-item>
        <!-- 评论 -->
        <div v-if="OpenId === item.id">
          <l-comments></l-comments>
        </div>
      </template>
    </a-list>
  </a-card>
</template>
<script lang="ts" setup>
import { LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
import { ref } from 'vue';
const listData = ref<Record<string, any>[]>([]);
listData.value.push({
  id: 0,
  name: 'Roxy',
  date: '2024-08-20 01:54',
  avatar: '/src/assets/image/roxy.jpg',
  content:
    "欢迎来到 Loyal's Blog，一个致力于分享知识、见解和生活点滴的数字空间。在这里，您将遇见 Loyal，一位对技术充满热情、对写作怀有深情的博主。Loyal 相信，通过文字，我们不仅能够记录生活，还能够启发思考，连接彼此。Loyal's Blog 涵盖了一系列主题，从最新科技趋势、编程技巧到个人成长和生活哲学，每一篇文章都是 Loyal 对世界的独到见解和真诚分享。无论您是技术爱好者、终身学习者，还是寻找生活灵感的旅人，这里都有您感兴趣的内容。我们承诺，每一篇文章都将用心去撰写，每一次更新都将带来价值。加入我们，一起探索知识的海洋，体验成长的喜悦，感受生活的美好。感谢您的访问，期待与您在字里行间相遇。",
  likes: 9999,
  isLike: false,
  comments: 4
});
for (let i = 0; i < 23; i++) {
  listData.value.push({
    id: i + 1,
    name: 'Roxy',
    date: '2024-08-20 01:54',
    avatar: '/src/assets/image/roxy.jpg',
    content: '博客项目，启动！！！',
    likes: 156,
    isLike: false,
    comments: 4
  });
}
const pagination = {
  onChange: (page: number) => {
    OpenId.value = -1;
  },
  pageSize: 5
};

const handleLike = (item: any) => {
  if (item.isLike) {
    item.likes--;
    item.isLike = false;
  } else {
    item.likes++;
    item.isLike = true;
  }
};
const OpenId = ref(-1);
const handleComement = (item: any) => {
  if (OpenId.value !== item.id) {
    OpenId.value = item.id;
  } else {
    OpenId.value = -1;
  }
};
</script>

<style lang="scss">
.header {
  text-align: center;
  padding: 1rem 0;
  border-bottom: 2px solid #dededf;
}

.ant-list-item-meta-title {
  color: #88a8f3 !important;
  margin-bottom: 0.5rem !important;
}

.action {
  cursor: pointer;
  -webkit-user-select: none; /* Chrome/Safari */
  -moz-user-select: none; /* Firefox */
  -ms-user-select: none; /* IE/Edge */
  user-select: none; /* Standard syntax */
  &:hover {
    color: #ff3030 !important;
  }
}
.active {
  color: #ff3030 !important;
}
</style>
