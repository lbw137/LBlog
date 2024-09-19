<template>
  <a-layout-footer>
    <div class="top">
      <div class="introduction">
        <h2>Loyal's Blog</h2>
        <p>
          大家好！我叫lyb，这是我开发的个人博客网站，我希望能用它来记录我学习、生活和工作的点滴。
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

<style scoped lang="scss">
.ant-layout-footer {
  background-color: $footer-color;
  color: $footer-h-color;
  padding: 0;
  min-width: $minWidth;
  .top {
    margin: 0 auto;
    padding: 5% 10%;
    display: flex;
    flex-wrap: wrap;
    .introduction {
      flex: 2;
      padding-right: 3rem;
      p {
        color: $footer-p-color;
        line-height: 2.4rem;
        font-size: $footer-p-size;
      }
    }
    .artical,
    .media {
      flex: 1;
      padding: 0 1.5rem;
    }
    .contact {
      img {
        width: $QRcode-width;
        border-radius: 0.2rem;
      }
    }
    h2 {
      font-size: $footer-h-size;
      margin-bottom: 2rem;
    }
    .introduction h2 {
      font-size: $footer-logo-size;
    }
    ul li {
      margin-top: 1rem;
      line-height: $footer-li-height;
    }
  }
  .bottom {
    padding: 1% 5%;
    border-top: 1px solid rgba(255, 255, 255, 0.1);
    display: flex;
    justify-content: space-between;
    p {
      min-width: auto;
      font-size: $footer-p-size;
      color: $footer-p-color;
    }
    ul {
      min-width: auto;
      li {
        display: inline;
        margin-left: 2rem;
      }
    }
  }
}

a {
  color: $footer-p-color;
  font-size: $footer-p-size;
  &:hover {
    color: $footer-h-color;
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
