<template>
  <div>
    <ul>
      <li>
        <a-badge-ribbon text="分类列表" placement="start" style="top: 8rem">
          <a-card>
            <h1 class="title"><a href="#">Vue3知识点</a></h1>
            <a-space class="info" size="middle">
              <span>2024-09-19</span>
              <span>2333</span>
              <span>字数≈999字</span>
              <span>阅读时长≈5分</span>
            </a-space>
            <p>
              <img src="@/assets/image/bg.png" alt="" />
              <LButton>阅读全文</LButton>
            </p>
            <div class="footer">
              <a-space wrap size="large">
                <LTag text="666" color="#38dbfb"></LTag>
                <LTag text="666" color="#cae"></LTag>
                <LTag text="666" color="#cae"></LTag>
                <LTag text="666" color="#cae"></LTag>
                <LTag text="666" color="#cae"></LTag>
              </a-space>
            </div>
          </a-card>
        </a-badge-ribbon>
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
li {
  .ant-card {
    padding: 1rem 3rem 2rem;
    text-align: center;
    h1 {
      font-size: 3rem;
      cursor: pointer;
      a {
        display: inline-block;
        transition: transform 0.3s ease;
        &:hover {
          transform: scale(1.1);
        }
      }
    }
    span {
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
      margin-top: 2rem;
      text-align: left;
      margin-left: 1rem;
    }
  }
}
</style>
