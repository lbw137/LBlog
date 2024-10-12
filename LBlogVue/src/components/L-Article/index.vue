<template>
  <div class="l-article">
    <ul>
      <li v-for="item in data" :key="item.id">
        <a-card>
          <!-- 标题 -->
          <h1 class="title" @click="onTitleClick(item)">
            {{ item.title }}
          </h1>
          <!-- 信息 -->
          <a-space class="info" size="middle">
            <span class="date">
              <a-space>
                <CalendarFilled />
                {{ dayjs(item.createTime).format('YYYY-MM-DD') }}
              </a-space>
            </span>
            <span class="views">
              <a-space>
                <EyeFilled />
                {{ item.views }}
              </a-space>
            </span>
            <span>
              <a-space>
                <EditFilled />
                字数≈{{ item.letters }}字
              </a-space>
            </span>
            <span>
              <a-space>
                <ClockCircleFilled />
                阅读时长≈{{ item.readTime }}分
              </a-space>
            </span>
          </a-space>
          <!-- 分类 -->
          <div
            class="category"
            :style="{
              '--catColor': item.category.color
            }"
            @click="onCatClick(item.category)"
          >
            {{ item.category.title }}
          </div>
          <!-- 图片+按钮 -->
          <p>
            <a-image :src="item.cover" />
            <LButton @click="handleClick(item.id)">阅读全文</LButton>
          </p>
          <!-- 标签 -->
          <div class="footer">
            <l-tag
              v-for="tag in item.tags"
              :text="tag.title"
              :color="tag.color"
              @click="onTagClick(tag)"
            ></l-tag>
          </div>
        </a-card>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import {
  CalendarFilled,
  EyeFilled,
  EditFilled,
  ClockCircleFilled
} from '@ant-design/icons-vue';
import { useRouter } from 'vue-router';
import type { BlogList } from '@/api/client/blog/type';
import { Tag } from '@/api/client/siteInfo/type';
import dayjs from 'dayjs';
const $router = useRouter();
defineProps({
  data: {
    type: Array<BlogList>
  }
});
const handleClick = (id: number) => {
  $router.push('/detail/' + id);
};
const onTitleClick = (item: BlogList) => {
  $router.push('/detail/' + item.id);
};
const onTagClick = (tag: Tag) => {
  $router.push({
    path: `/tag/${tag.title}`,
    query: {
      id: tag.id,
      color: tag.color
    }
  });
};
const onCatClick = (category: Tag) => {
  $router.push({
    path: `/category/${category.title}`,
    query: {
      id: category.id,
      color: category.color
    }
  });
};
</script>

<style scoped lang="scss">
li {
  margin-bottom: 4rem;
  .ant-card {
    padding: 1rem 3rem 0;
    text-align: center;
    .title {
      width: fit-content;
      margin: 0 auto;
      font-size: 2.4rem;
      cursor: pointer;
      transition: transform 0.3s ease;
      &:hover {
        transform: scale(1.1);
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
    .category {
      position: absolute;
      user-select: none;
      color: #fff;
      padding: 0 0.5rem;
      border-radius: 0.3rem;
      border-bottom-left-radius: 0;
      font-size: 1.5rem;
      left: -1rem;
      background-color: var(--catColor);
      &:hover {
        box-shadow: $tag-shadow;
      }
      &::before {
        content: '';
        position: absolute;
        left: 0rem;
        bottom: -0.9rem;
        border: 0.5rem solid var(--catColor);
        filter: brightness(90%);
        border-left-color: transparent;
        border-bottom-color: transparent;
      }
    }
    p {
      margin-top: 3rem;
      border-bottom: 2px solid #dededf;
      img {
        width: 100%;
      }
      .l-btn {
        margin: 3rem 0 1rem;
      }
    }
    .footer {
      text-align: left;
      margin-top: 1.5rem;
      margin-left: 1rem;
      display: flex;
      flex-wrap: wrap;
      .l-tag {
        margin-right: 2rem;
        margin-bottom: 1.5rem;
      }
    }
  }
}
</style>
