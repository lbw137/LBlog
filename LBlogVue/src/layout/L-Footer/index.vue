<template>
  <a-layout-footer>
    <div class="top">
      <div class="introduction">
        <h2>Loyal's Blog</h2>
        <p>
          大家好！我叫lyb，这个Loyal's
          Blog正是用了我名字的首字母，我希望能用它来记录我学习、生活和工作的点滴。
        </p>
      </div>
      <div class="artical">
        <h2>精选笔记</h2>
        <ul>
          <li><a href="#">Vue3常用知识点</a></li>
          <li><a href="#">常用的组件库</a></li>
          <li><a href="#">生活日记</a></li>
        </ul>
      </div>
      <div class="media">
        <h2>关注我们</h2>
        <ul>
          <li><a href="#">微信号</a></li>
          <li><a href="#">哔哩哔哩</a></li>
        </ul>
      </div>
      <div class="contact">
        <h2>联系我们</h2>
        <img src="../../assets/image/qrCode.png" alt="" />
      </div>
    </div>
    <div class="bottom">
      <p>© Copyright 2024 LOYALTY</p>
      <ul>
        <li><a href="#">隐私政策</a></li>
        <li><a href="#">服务协议</a></li>
      </ul>
    </div>
  </a-layout-footer>
</template>

<script setup lang="ts"></script>

<style scoped>
.ant-layout-footer {
  background-color: #202942;
  color: #f8f9fa;
  padding: 0;
  .top {
    margin: 0 auto;
    padding: 5% 10%;
    display: flex;
    flex-wrap: wrap;
    .introduction {
      flex: 2;
      padding-right: 3rem;
      p {
        color: #adb5bd;
        line-height: 2.4rem;
        font-size: 1.4rem;
      }
    }
    .artical,
    .media {
      flex: 1;
      padding: 0 1.5rem;
    }
    .contact {
      img {
        width: 9.6rem;
        border-radius: 0.2rem;
      }
    }
    h2 {
      font-size: 1.6rem;
      margin-bottom: 2rem;
    }
    .introduction h2 {
      font-size: 2rem;
    }
    ul li {
      margin-top: 1rem;
      line-height: 2.4rem;
    }
  }
  .bottom {
    padding: 1% 10%;
    border-top: 1px solid rgba(255, 255, 255, 0.1);
    display: flex;
    justify-content: space-between;
    p {
      min-width: 1.7rem;
      font-size: 1.4rem;
      color: #adb5bd;
    }
    ul li {
      display: inline;
      margin-left: 2rem;
    }
  }
}

a {
  color: #adb5bd;
  font-size: 1.4rem;
  &:hover {
    color: #f8f9fa;
  }
}

@media (max-width: 767px) {
  .top {
    flex-direction: column;
    .introduction,
    .artical,
    .media,
    .contact {
      text-align: center;
      margin-bottom: 1rem;
    }
    .introduction {
      padding: 0 !important;
    }
  }
}
</style>
