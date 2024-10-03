<template>
  <div class="l-comments">
    <!-- 发表评论 -->
    <a-comment id="comment-submit" style="padding: 1rem 2rem 0">
      <template #avatar>
        <a-avatar src="/src/assets/image/roxy.jpg" alt="Roxy" />
      </template>
      <template #content>
        <a-form-item>
          <a-textarea
            v-model:value="commentValue"
            :rows="2"
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
          <a-button
            danger
            @click="handleCancel"
            style="margin-left: 2rem"
            v-if="Reply.isReply"
          >
            取消回复
          </a-button>
        </a-form-item>
      </template>
    </a-comment>
    <!-- 评论数量 -->
    <slot></slot>
    <!-- 评论列表 -->
    <a-list
      class="comment-list"
      :data-source="data"
      size="small"
      style="padding: 0 2rem"
      :pagination="comPagination"
    >
      <template #renderItem="{ item }">
        <a-list-item :id="'comment' + item.id">
          <a-comment :author="item.author" :avatar="item.avatar">
            <template #actions>
              <a
                href="#comment-submit"
                @click="handleSwitch($event, true, item)"
              >
                回复
              </a>
            </template>
            <template #content>
              <p>
                <a
                  :href="'#comment' + item.replyId"
                  @click="handleSwitch"
                  v-if="item.replyName"
                  class="reply-a"
                >
                  @{{ item.replyName }}
                </a>
                {{ item.content }}
              </p>
            </template>
            <template #datetime>
              <a-tooltip :title="item.datetime.format('YYYY-MM-DD HH:mm:ss')">
                <span>{{ item.datetime.fromNow() }}</span>
              </a-tooltip>
            </template>
          </a-comment>
        </a-list-item>
      </template>
    </a-list>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed, nextTick } from 'vue';
import dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';
import { message } from 'ant-design-vue';
dayjs.extend(relativeTime);
const current = ref(1);
const pageSize = ref(3);
const comPagination = computed(() => ({
  onChange: (page: number) => {
    current.value = page;
  },
  pageSize: pageSize.value,
  current: current.value,
  simple: true
}));
const data = ref([
  {
    id: 1,
    author: 'Roxy',
    avatar: '/src/assets/image/roxy.jpg',
    content: '牛逼！！！',
    datetime: dayjs().subtract(1, 'days'),
    page: 1
  },
  {
    id: 2,
    author: '马德胜',
    avatar: '/src/assets/image/roxy.jpg',
    content: '无敌！！！',
    datetime: dayjs().subtract(2, 'days'),
    page: 1
  },
  {
    id: 3,
    author: '卢钲扬',
    avatar: '/src/assets/image/roxy.jpg',
    content: '无敌！！！',
    datetime: dayjs().subtract(2, 'days'),
    page: 1,
    replyName: 'Roxy',
    replyId: 1
  },
  {
    id: 4,
    author: '张霖',
    avatar: '/src/assets/image/roxy.jpg',
    content: '无敌！！！',
    datetime: dayjs().subtract(2, 'days'),
    page: 2
  }
]);
const submitting = ref<boolean>(false);
const commentValue = ref('');
const placeholder = ref('这里是评论区,不是无人区;-)');
const Reply = reactive({
  isReply: false,
  replyName: '',
  replyId: -1
});
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
    data.value.push({
      id: data.value[data.value.length - 1].id + 1,
      author: 'Roxy',
      avatar: '/src/assets/image/roxy.jpg',
      content: commentValue.value,
      datetime: dayjs(),
      page:
        (data.value.length + 1) % pageSize.value === 0
          ? (data.value.length + 1) / pageSize.value
          : (data.value.length + 1) / pageSize.value + 1,
      replyName: Reply.isReply ? Reply.replyName : '',
      replyId: Reply.isReply ? Reply.replyId : -1
    });
    commentValue.value = '';
    if (Reply.isReply) {
      Reply.isReply = false;
      placeholder.value = '这里是评论区,不是无人区;-)';
    }
  }, 1000);
};
// 利用二分查找搜索目标评论
const binarySearch = (arr: any, target: number) => {
  let left = 0;
  let right = arr.length - 1;

  while (left <= right) {
    let mid = Math.floor((left + right) / 2); // 计算中间索引
    let midValue = arr[mid].id; // 获取中间值
    if (midValue === target) {
      return mid; // 找到目标值，返回索引
    } else if (target < midValue) {
      right = mid - 1; // 目标值在左半部分
    } else {
      left = mid + 1; // 目标值在右半部分
    }
  }
  return -1; // 如果没有找到目标值，返回-1
};

// 跳转到目标评论
const handleSwitch = async (e: MouseEvent, reply?: boolean, item?: any) => {
  e.preventDefault();
  const a = e.currentTarget as HTMLAnchorElement;
  const targetId = a.getAttribute('href') as string;
  const id = targetId?.match(/\d+/) ? Number(targetId.match(/\d+/)?.[0]) : null;
  const index = binarySearch(data.value, Number(id));
  if (index !== -1) {
    current.value = data.value[index].page;
  }
  await nextTick();
  const target = document.querySelector(targetId) as HTMLElement;
  window.scrollTo({
    top: target.getBoundingClientRect().top + window.scrollY - 65,
    behavior: 'smooth'
  });
  if (reply) {
    Reply.isReply = true;
    Reply.replyName = item?.author;
    Reply.replyId = item?.id;
    placeholder.value = `回复 @${item?.author}：`;
    console.log(item);
  }
};
const handleCancel = () => {
  Reply.isReply = false;
  placeholder.value = '这里是评论区,不是无人区;-)';
};
</script>

<style scoped lang="scss">
.reply-a {
  color: #409eff;
  user-select: none;
  &:hover {
    color: $logo-color;
  }
}
</style>
