<template>
  <div class="l-details">
    <a-badge-ribbon :text="data.category" placement="start">
      <a-card>
        <!-- 文章标题 -->
        <div class="title">
          <h1>
            {{ data.title }}
          </h1>
        </div>
        <!-- 文章信息 -->
        <a-space class="info" size="middle">
          <span class="date">
            <a-space>
              <CalendarFilled />
              {{ data.date }}
            </a-space>
          </span>
          <span class="views">
            <a-space>
              <EyeFilled />
              {{ data.views }}
            </a-space>
          </span>
          <span>
            <a-space>
              <EditFilled />
              字数≈{{ data.letters }}字
            </a-space>
          </span>
          <span>
            <a-space>
              <ClockCircleFilled />
              阅读时长≈{{ data.time }}分
            </a-space>
          </span>
        </a-space>
        <!-- 文章内容 -->
        <div class="details-con">
          <!-- 仅预览插件 -->
          <MdPreview
            editorId="preview-only"
            :modelValue="data.content"
            class="md-preview"
            :showCodeRowNumber="true"
          />
          <!-- 赞赏按钮 -->
          <a-popover trigger="click">
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
            v-for="tag in data.tags"
            :text="tag[0]"
            :color="tag[1]"
          ></l-tag>
        </div>
        <!-- 署名 -->
        <ul class="sign">
          <li>作者：loyal <a href="#">（联系作者）</a></li>
          <li>发表时间：2024-06-11 16:06</li>
          <li>最后修改：2024-06-11 16:17</li>
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
        <l-comments>
          <h2
            style="
              padding-left: 2rem;
              border-bottom: 1px solid #dededf;
              margin-bottom: 2rem;
            "
          >
            共有 n 条评论
          </h2>
        </l-comments>
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
import { reactive, onMounted } from 'vue';
import { MdPreview } from 'md-editor-v3';
import 'md-editor-v3/lib/preview.css';
import $ from 'jquery';
const data = reactive({
  id: 1,
  title: 'Vue3知识点啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊',
  date: '2024-9-27',
  content: `# 卢本伟牛逼啊
  - 
  ## [百度一下，你就知道](https://www.baidu.com) - 123123
  - [ ] Friday
  ### 关于 Editor.md
  #### 阿松大
  ##### 啊实打实大苏打实打实大苏打
  ###### 阿里大家阿斯利康到家啦是可见的拉萨空间
**Editormd** 是一款开源的、可嵌入的 Markdown 在线编辑器（组件），基于 CodeMirror、jQuery 和 Marked 构建。`,
  views: 2333,
  letters: 1068,
  time: 5,
  tags: [
    ['666', '#38dbfb'],
    ['666', '#cae'],
    ['666', '#cae'],
    ['666', '#cae'],
    ['666', '#cae']
  ],
  category: '学习笔记'
});
onMounted(() => {
  $('.task-list-item-checkbox').removeAttr('disabled');
  $('.md-preview a').attr('target', '_blank');
});
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
  .l-comments {
    margin-top: 1rem;
  }
}
</style>
