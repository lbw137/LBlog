<template>
  <div class="l-timeline">
    <div class="l-tl-item">
      <div class="l-tl-head">2024年9月</div>
      <div class="l-tl-body">
        <!-- 第一个子元素 -->
        <div class="l-tl-blog">
          <span>22日</span>
          <div class="l-tl-con">
            <div class="l-tl-title">
              <p>Vue3知识点</p>
            </div>
          </div>
        </div>
        <!-- 第二个子元素 -->
        <div class="l-tl-blog">
          <span>10日</span>
          <div class="l-tl-con">
            <div class="l-tl-title">
              <p>博客项目，启动</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="l-tl-item">
      <div class="l-tl-head">2024年9月</div>
      <div class="l-tl-body">
        <div class="l-tl-blog">
          <span>22日</span>
          <div class="l-tl-con">
            <div class="l-tl-title">
              <p>Vue3知识点</p>
            </div>
          </div>
        </div>
        <div class="l-tl-blog">
          <span>10日</span>
          <div class="l-tl-con">
            <div class="l-tl-title">
              <p>博客项目，启动！！!</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="l-tl-item">
      <div class="l-tl-head">2024年9月</div>
      <div class="l-tl-body">
        <div class="l-tl-blog">
          <span>22日</span>
          <div class="l-tl-con">
            <div class="l-tl-title">
              <p>Vue3知识点</p>
            </div>
          </div>
        </div>
        <div class="l-tl-blog">
          <span>10日</span>
          <div class="l-tl-con">
            <div class="l-tl-title">
              <p>博客项目，启动！！!</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss">
$first-color: #23b7e5;
$second-color: #3a3f51;
$third-color: #27c24c;
$fourth-color: #7266ba;
$fifth-color: #f05050;

.l-timeline {
  margin: 2rem 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #fff;
  @for $i from 1 through 5 {
    .l-tl-item:nth-child(5n + #{$i}) {
      $prime-color: null;
      @if $i == 1 {
        $prime-color: $first-color;
      } @else if $i == 2 {
        $prime-color: $second-color;
      } @else if $i == 3 {
        $prime-color: $third-color;
      } @else if $i == 4 {
        $prime-color: $fourth-color;
      } @else if $i == 5 {
        $prime-color: $fifth-color;
      }
      display: flex;
      align-items: center;
      flex-direction: column;
      width: 90%;
      .l-tl-head {
        background-color: $prime-color;
        border-radius: 0.5rem;
        padding: 0.5rem 1rem;
      }
      .l-tl-body {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: inherit;
        .l-tl-blog {
          position: relative;
          display: flex;
          width: inherit;
          justify-content: center;
          span {
            position: absolute;
            @if $i%2 != 0 {
              left: 50%;
              transform: translate(1.5rem, -50%);
            } @else {
              right: 50%;
              transform: translate(-1.5rem, -50%);
            }
            top: 50%;
            color: #000;
          }
          .l-tl-con {
            position: relative;
            padding: 2rem;
            width: 70%;
            @if $i%2 != 0 {
              transform: translateX(calc(-50% + 2px));
              border-right: 0.4rem solid $prime-color;
            } @else {
              transform: translateX(calc(50% - 2px));
              border-left: 0.4rem solid $prime-color;
            }
            .l-tl-title {
              display: flex;
              p {
                max-width: 100%;
                position: relative;
                background-color: $prime-color;
                padding: 0.8rem;
                border-radius: 0.5rem;
                word-break: break-word;
                @if $i%2 != 0 {
                  margin-left: auto;
                } @else {
                  margin-right: auto;
                }
                &::before {
                  content: '';
                  position: absolute;
                  display: block;
                  width: 1rem;
                  height: 1rem;
                  background-color: inherit;
                  top: 50%;
                  @if $i%2 != 0 {
                    right: 0;
                    transform: translate(50%, -50%) rotate(45deg);
                  } @else {
                    left: 0;
                    transform: translate(-50%, -50%) rotate(45deg);
                  }
                }
              }
            }
          }
          &::before {
            content: '';
            position: absolute;
            width: 1rem;
            height: 1rem;
            border-radius: 50%;
            border: 0.3rem solid $prime-color;
            box-shadow: 0 0 0 0.4rem #fff;
            z-index: 1;
            background-color: #fff;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
          }
        }
      }
    }
  }
  &::after {
    content: 'Hello World!';
    display: block;
    text-align: center;
    background-color: black;
    border-radius: 0.5rem;
    padding: 0.5rem 1rem;
  }
}
</style>
