<template>
  <div class="l-article">
    <ul>
      <li v-for="item in data" :key="item.date">
        <a-badge-ribbon
          :text="item.category"
          placement="start"
          style="top: 8rem"
        >
          <a-card>
            <h1 class="title">
              <a href="#">{{ item.title }}</a>
            </h1>
            <a-space class="info" size="middle">
              <span class="date">
                <a-space>
                  <CalendarFilled />
                  {{ item.date }}
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
                  阅读时长≈{{ item.time }}分
                </a-space>
              </span>
            </a-space>
            <p>
              <img :src="item.img" alt="" />
              <LButton>阅读全文</LButton>
            </p>
            <div class="footer">
              <l-tag
                v-for="tag in item.tags"
                :text="tag[0]"
                :color="tag[1]"
              ></l-tag>
            </div>
          </a-card>
        </a-badge-ribbon>
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
const data = [
  {
    title: 'Vue3知识点123123123123123',
    date: '2024-09-19',
    views: 2333,
    letters: '1068',
    time: '5',
    img: '/src/assets/image/bg.png',
    category: '分类列表',
    tags: [
      ['666', '#38dbfb'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae']
    ]
  },
  {
    title: 'Vue3知识点',
    date: '2024-09-19',
    views: 2333,
    letters: '1068',
    time: '5',
    img: '/src/assets/image/bg.png',
    category: '分类列表',
    tags: [
      ['666', '#38dbfb'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae']
    ]
  },
  {
    title: 'Vue3知识点',
    date: '2024-09-19',
    views: 2333,
    letters: '1068',
    time: '5',
    img: '/src/assets/image/bg.png',
    category: '分类列表',
    tags: [
      ['666', '#38dbfb'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae']
    ]
  },
  {
    title: 'Vue3知识点',
    date: '2024-09-19',
    views: 2333,
    letters: '1068',
    time: '5',
    img: '/src/assets/image/bg.png',
    category: '分类列表',
    tags: [
      ['666', '#38dbfb'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae'],
      ['666', '#cae']
    ]
  }
];
</script>

<style scoped>
li {
  margin-bottom: 4rem;
  .ant-card {
    padding: 1rem 3rem 0;
    text-align: center;
    h1 {
      font-size: 2.4rem;
      cursor: pointer;
      a {
        display: inline-block;
        transition: transform 0.3s ease;
        &:hover {
          transform: scale(1.1);
        }
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
