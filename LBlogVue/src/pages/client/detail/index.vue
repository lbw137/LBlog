<template>
  <div class="l-details">
    <a-badge-ribbon :text="data?.category.title" placement="start">
      <a-card>
        <!-- 文章标题 -->
        <div class="title">
          <h1>
            {{ data?.title }}
          </h1>
        </div>
        <!-- 文章信息 -->
        <a-space class="info" size="middle">
          <span class="date">
            <a-space>
              <CalendarFilled />
              {{ dayjs(data?.createTime).format('YYYY-MM-DD') }}
            </a-space>
          </span>
          <span class="views">
            <a-space>
              <EyeFilled />
              {{ data?.views }}
            </a-space>
          </span>
          <span>
            <a-space>
              <EditFilled />
              字数≈{{ data?.letters }}字
            </a-space>
          </span>
          <span>
            <a-space>
              <ClockCircleFilled />
              阅读时长≈{{ data?.readTime }}分
            </a-space>
          </span>
        </a-space>
        <!-- 文章内容 -->
        <div class="details-con">
          <!-- 仅预览插件 -->
          <MdPreview
            editorId="preview-only"
            :modelValue="data?.content"
            class="md-preview"
            :showCodeRowNumber="true"
          />
          <!-- 赞赏按钮 -->
          <a-popover trigger="click" v-if="data?.isCommend">
            <template #content>
              <div>
                <h3 style="text-align: center; color: #f17000">
                  <span>感谢老板(੭˙ᗜ˙)੭</span>
                </h3>
                <img
                  src="@/assets/image/commend.jpg"
                  alt=""
                  style="width: 15rem"
                />
              </div>
            </template>
            <div class="commend">赞赏</div>
          </a-popover>
        </div>
        <!-- 文章底部 -->
        <div class="details-footer">
          <!-- 标签 -->
          <l-tag
            v-for="tag in data?.tags"
            :text="tag.title"
            :color="tag.color"
            @click="onTagClick(tag)"
          ></l-tag>
        </div>
        <!-- 署名 -->
        <ul class="sign">
          <li>作者：loyal <a href="#">（联系作者）</a></li>
          <li>
            发表时间：{{ dayjs(data?.publishTime).format('YYYY-MM-DD HH:mm') }}
          </li>
          <li>
            最后修改：{{ dayjs(data?.updateTime).format('YYYY-MM-DD HH:mm') }}
          </li>
          <li>
            本站点采用<a
              href="https://creativecommons.org/licenses/by/4.0/"
              target="_blank"
            >
              署名 4.0 国际 (CC BY 4.0)</a
            >
            创作共享协议。可自由转载、引用，并且允许商业性使用。但需署名作者且注明文章出处。
          </li>
        </ul>
        <!-- 文章评论 -->
        <div class="comments" v-if="data?.isReview">
          <!-- 发表评论 -->
          <a-comment id="comment-submit" style="padding: 1rem 2rem 0">
            <template #avatar>
              <a-avatar
                src="/src/assets/image/roxy.jpg"
                alt="Roxy"
                size="large"
              />
            </template>
            <template #content>
              <a-form-item>
                <a-textarea
                  v-model:value="commentValue"
                  :rows="5"
                  :placeholder="placeholder"
                />
              </a-form-item>
              <a-form-item>
                <a-button
                  html-type="submit"
                  :loading="submitting"
                  type="primary"
                  @click="handleSubmit"
                >
                  发表评论
                </a-button>
              </a-form-item>
            </template>
          </a-comment>
          <!-- 评论列表 -->
          <h2 c>Comments | 共 25 条评论</h2>
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
                    <a-avatar :src="item.avatar" />
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
        </div>
      </a-card>
    </a-badge-ribbon>
  </div>
</template>

<script setup lang="ts">
import {
  CalendarFilled,
  EyeFilled,
  EditFilled,
  ClockCircleFilled
} from '@ant-design/icons-vue';
import { ref, watchEffect } from 'vue';
import { MdPreview } from 'md-editor-v3';
import 'md-editor-v3/lib/preview.css';
import { useRoute, useRouter } from 'vue-router';
import { BlogDetail } from '@/api/client/blog/type';
import { reqDetBlog } from '@/api/client/blog';
import dayjs from 'dayjs';
import { Tag } from '@/api/client/siteInfo/type';
import { LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
import { message } from 'ant-design-vue';

const $route = useRoute();
const $router = useRouter();
const data = ref<BlogDetail>();
const getData = async () => {
  const res = await reqDetBlog(Number($route.params.id));
  if (res.code === 200) data.value = res.data.blog;
};
watchEffect(async () => {
  if ($route.params.id) {
    getData();
  }
});
const onTagClick = (tag: Tag) => {
  $router.push({
    path: `/tag/${tag.title}`,
    query: {
      id: tag.id,
      color: tag.color
    }
  });
};

// 发表评论
const submitting = ref<boolean>(false);
const commentValue = ref('');
const placeholder = ref('这里是评论区,不是无人区;-)');
const handleSubmit = () => {
  // 直接评论不需要@，回复评论需要@
  if (!commentValue.value) {
    message.warning({
      content: '评论内容不能为空！',
      style: {
        marginTop: '10vh'
      }
    });
    return;
  }
  submitting.value = true;
  setTimeout(() => {
    submitting.value = false;
    listData.value.push({
      id: listData.value[listData.value.length - 1].id + 1,
      name: 'Roxy',
      date: dayjs().format('YYYY-MM-DD HH:mm'),
      avatar: '/src/assets/image/roxy.jpg',
      content: commentValue.value,
      likes: 156,
      isLike: false,
      comments: 4
    });
    commentValue.value = '';
  }, 1000);
};

// 评论列表
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

<style scoped lang="scss">
.ant-card {
  padding: 1rem 0 0;
  .title {
    padding: 0 2rem;
    h1 {
      font-size: 2.4rem;
      cursor: pointer;
      width: fit-content;
      margin: 0 auto;
      &:hover {
        transform: scale(1.1);
      }
      transition: transform 0.3s ease;
    }
  }
  .info {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    margin-top: 1rem;
    .date {
      color: #45cfe7;
    }
    .views {
      color: #ff6363;
    }
    span {
      text-wrap: nowrap;
    }
  }
  .details-con {
    .commend {
      margin: 5rem auto;
      width: 7rem;
      height: 3.6rem;
      line-height: 3.4rem;
      user-select: none;
      color: #ff7701;
      border: 2px solid #ff7701;
      text-align: center;
      border-radius: 14rem;
      &:hover {
        background-color: #ff7701;
        color: #fff;
      }
    }
  }
  .details-footer {
    width: 90%;
    margin: 2rem auto 0;
    padding: 2rem 1rem 1rem;
    border-top: 2px solid #dededf;
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    .l-tag {
      margin-right: 2rem;
      margin-bottom: 1rem;
    }
  }
  .sign {
    background-color: #fcfff5;
    border: 1px solid #a3c293;
    border-bottom: 2px solid #00b5ad;
    color: #2c662d;
    padding: 1rem 3rem;
    transform: scaleX(1.004);
    li {
      list-style: disc;
    }
    a {
      color: #79a7d2;
    }
  }
  .comments {
    h2 {
      padding: 0 2%;
      border-bottom: 1px solid #dededf;
    }
    .ant-list {
      margin-top: 1.5rem !important;
    }
    :deep(.ant-list-item) {
      padding: 0.9rem 1.8rem !important;
    }
    :deep(.ant-list-item-meta) {
      margin-bottom: 0.6rem !important;
    }
    :deep(.ant-list-item-action) {
      margin-top: 0.6rem !important;
    }
    :deep(.ant-list-item-meta-title) {
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
  }
}
</style>
