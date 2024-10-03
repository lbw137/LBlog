<template>
  <div class="blog-pub">
    <a-card>
      <div class="blog-pub-header">
        <span>博客发布</span>
      </div>
      <a-form
        ref="formRef"
        :model="formState"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 8 }"
        autocomplete="off"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <!-- 标题 -->
        <a-form-item
          label="标题"
          name="title"
          :rules="[{ required: true, message: '请输入文章标题' }]"
        >
          <a-input v-model:value="formState.title" />
        </a-form-item>
        <!-- MarkDown插件 -->
        <a-form-item name="content" :wrapper-col="{ span: 24 }">
          <div class="markdown">
            <MdEditor v-model="formState.content" @onUploadImg="onUploadImg">
            </MdEditor>
          </div>
        </a-form-item>
        <!-- 分类 -->
        <a-form-item
          name="category"
          label="分类"
          :rules="[{ required: true, message: '请选择文章分类' }]"
        >
          <a-select
            v-model:value="formState.category"
            :options="catOptions"
          ></a-select>
        </a-form-item>
        <!-- 标签 -->
        <a-form-item
          name="tags"
          label="标签"
          :rules="[
            { required: true, message: '请至少选择一项标签', type: 'array' }
          ]"
        >
          <a-select
            v-model:value="formState.tags"
            :options="tagOptions"
            :fieldNames="{ label: 'text', value: 'id' }"
            placeholder="请选择标签"
            :filter-option="filterOption"
            mode="multiple"
          >
            <template #option="{ text }">
              {{ text }}
            </template>
            <template #tagRender="{ option, closable, onClose }">
              <a-tag
                :closable="closable"
                style="margin-right: 3px"
                @close="onClose"
                :color="option.color"
              >
                {{ option.text }}
              </a-tag>
            </template>
          </a-select>
        </a-form-item>
        <!-- 状态 -->
        <a-form-item label="状态">
          <a-row style="height: 3.2rem">
            <a-form-item name="commendable" style="margin-right: 2rem">
              <a-checkbox v-model:checked="formState.commendable">
                赞赏
              </a-checkbox>
            </a-form-item>
            <a-form-item name="reviewable" style="margin-right: 2rem">
              <a-checkbox v-model:checked="formState.reviewable">
                评论
              </a-checkbox>
            </a-form-item>
            <a-form-item name="topped">
              <a-checkbox v-model:checked="formState.topped">置顶</a-checkbox>
            </a-form-item>
          </a-row>
        </a-form-item>
        <!-- 封面 -->
        <a-form-item
          label="封面"
          name="cover"
          :rules="[{ required: true, message: '请选择文章封面' }]"
        >
          <l-upload @update="getCover"></l-upload>
        </a-form-item>
        <!-- 按钮 -->
        <a-form-item :wrapper-col="{ span: 24 }">
          <a-row justify="end">
            <a-button type="primary" html-type="submit">发布</a-button>
            <a-button @click="onSave" style="margin: 0 4rem"> 保存 </a-button>
          </a-row>
        </a-form-item>
      </a-form>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue';
import type { SelectProps } from 'ant-design-vue';
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
interface FormState {
  title: string; // 标题
  content: string; // 内容
  category: string; // 分类
  tags: string[]; // 标签
  cover: File | null; // 封面
  commendable: boolean; // 赞赏
  reviewable: boolean; // 评论
  topped: boolean; // 置顶
}
const onUploadImg = (files: File[]) => {
  console.log(files);
};
const formState = reactive<FormState>({
  title: '',
  content: `### 关于 Editor.md

**Editor.md** 是一款开源的、可嵌入的 Markdown 在线编辑器（组件），基于 CodeMirror、jQuery 和 Marked 构建。`,
  category: '学习笔记',
  tags: [],
  cover: null,
  commendable: true,
  reviewable: true,
  topped: false
});
const formRef = ref();
const onFinish = (values: FormState) => {
  console.log(values);
};
const onFinishFailed = (errorInfo: any) => {
  console.log(errorInfo);
};
const onSave = async () => {
  formRef.value
    .validate()
    .then(() => {
      console.log('values', formState);
    })
    .catch((error) => {
      console.log('error', error);
    });
};
// 分类
const catOptions = ref<SelectProps['options']>([
  {
    value: '学习笔记',
    label: '学习笔记'
  },
  {
    value: '生活日记',
    label: '生活日记'
  }
]);

// 标签
const tagOptions = ref<SelectProps['options']>([
  {
    id: '1',
    text: 'javascript',
    color: '#f50'
  },
  {
    id: '2',
    text: 'vue',
    color: '#2db7f5'
  }
]);
const filterOption = (input: string, option: any) => {
  return option.text.toLowerCase().indexOf(input.toLowerCase()) >= 0;
};

// 封面
const getCover = (file: File) => {
  formState.cover = file;
};
</script>

<style scoped lang="scss">
.blog-pub {
  display: flex;
  justify-content: center;
  padding: 2rem 0;
  .ant-card {
    width: 90%;
    &:hover {
      box-shadow: none !important;
    }
    .blog-pub-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 6.5rem;
      padding: 0 1%;
      border-bottom: 1px solid #e8e8e8;
      span {
        color: $logo-color;
        font-weight: bolder;
        font-size: 2.5rem;
        text-wrap: nowrap;
      }
    }
    .ant-form {
      padding: 2rem;
    }
    .markdown {
      :deep(ol) {
        list-style: decimal;
      }
      :deep(ul) {
        list-style: disc;
      }
    }
  }
}
</style>
