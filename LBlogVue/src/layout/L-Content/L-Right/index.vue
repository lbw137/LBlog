<template>
  <div class="right">
    <a-card style="margin-bottom: 2rem">
      <template #title
        ><BookOutlined style="margin-right: 1rem" />随机文章</template
      >
      <ul>
        <li>
          <div class="img"></div>
          <div class="msg">
            <p>2024-09-18</p>
            <p>Vue3常用知识点</p>
          </div>
        </li>
        <li>
          <div class="img"></div>
          <div class="msg">
            <p>2024-09-18</p>
            <p>Vue3常用知识点</p>
          </div>
        </li>
        <li>
          <div class="img"></div>
          <div class="msg">
            <p>2024-09-18</p>
            <p>Vue3常用知识点asdasdasdasdasdasd</p>
          </div>
        </li>
      </ul>
    </a-card>
    <a-card>
      <template #title
        ><TagsOutlined style="margin-right: 1rem" />标签云</template
      >
      <div class="tags">
        <a-tag color="#f50">#f50</a-tag>
        <a-tag color="#2db7f5">#2db7f5</a-tag>
        <a-tag color="#87d068">#87d068</a-tag>
        <a-tag color="#2fc">#2fc</a-tag>
        <a-tag color="#f50">#f50</a-tag>
        <a-tag color="#2db7f5">#2db7f5</a-tag>
        <a-tag color="#87d068">#87d068</a-tag>
        <a-tag color="#108ee9">#108ee9</a-tag>
      </div>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { BookOutlined, TagsOutlined } from '@ant-design/icons-vue';
</script>

<style scoped lang="scss">
.right {
  width: 20%;
  border-radius: 3%;
  ul {
    display: flex;
    padding: 1rem;
    padding-bottom: 0;
    /* 关键 */
    flex-wrap: wrap;
    li {
      position: relative;
      /* 关键 */
      width: 100%;
      margin-bottom: 1.5rem;
      transition: transform 0.3s ease;
      &:hover {
        box-shadow: $card-shadow;
      }
      .img {
        height: 10rem;
        background: url('@/assets/image/cover.png') center;
        background-size: cover;
        border-radius: 0.5rem;
        box-shadow: inset 0 0 2rem rgba(0, 0, 0, 0.7);
        cursor: pointer;
      }
      .msg {
        position: absolute;
        left: 1rem;
        bottom: 0.5rem;
        color: #fff;
        width: 90%;
        p {
          text-overflow: ellipsis;
          overflow: hidden;
          text-wrap: nowrap;
        }
      }
    }
  }
  .tags {
    padding: 1rem;
    padding-bottom: 0;
    .ant-tag {
      margin-bottom: 1em;
    }
  }
}
</style>
