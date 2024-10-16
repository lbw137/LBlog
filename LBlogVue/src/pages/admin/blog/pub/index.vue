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
          name="categoryId"
          label="分类"
          :rules="[{ required: true, message: '请选择文章分类' }]"
        >
          <a-select
            v-model:value="formState.categoryId"
            :options="catOptions"
          ></a-select>
        </a-form-item>
        <!-- 标签 -->
        <a-form-item
          name="tags"
          label="标签"
          :rules="[
            { required: true, message: '请至少选择一项标签', type: 'array' },
            { max: 5, message: '最多选择5个标签', type: 'array' }
          ]"
        >
          <a-select
            v-model:value="formState.tags"
            :options="tagOptions"
            :fieldNames="{ label: 'title', value: 'id' }"
            placeholder="请选择标签"
            :filter-option="filterOption"
            mode="multiple"
          >
            <template #option="{ title }">
              {{ title }}
            </template>
            <template #tagRender="{ option, closable, onClose }">
              <a-tag
                :closable="closable"
                style="margin-right: 3px"
                @close="onClose"
                :color="option.color"
              >
                {{ option.title }}
              </a-tag>
            </template>
          </a-select>
        </a-form-item>
        <!-- 状态 -->
        <a-form-item label="状态">
          <a-row style="height: 3.2rem" :wrap="false">
            <a-form-item name="isCommend" style="margin-right: 2rem">
              <a-checkbox v-model:checked="formState.isCommend">
                赞赏
              </a-checkbox>
            </a-form-item>
            <a-form-item name="isReview" style="margin-right: 2rem">
              <a-checkbox v-model:checked="formState.isReview">
                评论
              </a-checkbox>
            </a-form-item>
            <a-form-item name="isTop">
              <a-checkbox v-model:checked="formState.isTop">置顶</a-checkbox>
            </a-form-item>
          </a-row>
        </a-form-item>
        <!-- 封面 -->
        <a-form-item
          label="封面"
          name="cover"
          :rules="[{ required: !$route.query.id, message: '请选择文章封面' }]"
        >
          <l-upload @update="getCover"></l-upload>
        </a-form-item>
        <!-- 按钮 -->
        <a-form-item :wrapper-col="{ span: 24 }">
          <a-row justify="end" wrap="false">
            <a-button
              type="primary"
              html-type="submit"
              style="margin-right: 4%"
            >
              {{ $route.query.id ? '更新' : '发布' }}
            </a-button>
            <a-button
              @click="onSave"
              style="margin-right: 4%"
              v-if="!$route.query.id"
            >
              保存
            </a-button>
            <a-button
              @click="resetForm"
              type="primary"
              danger
              style="margin-right: 4%"
            >
              重置
            </a-button>
          </a-row>
        </a-form-item>
      </a-form>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { computed, reactive, ref, watchEffect } from 'vue';
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import { reqUpload } from '@/api/admin/upload';
import { useSiteInfo } from '@/store/useSiteInfo';
import { storeToRefs } from 'pinia';
import { BlogPub, BlogRes } from '@/api/admin/blog/type';
import { reqBlogById, reqBlogPub, reqBlogUpdate } from '@/api/admin/blog';
import { message } from 'ant-design-vue';
import { useRouter, useRoute } from 'vue-router';
const $route = useRoute();
const $router = useRouter();
const $site = useSiteInfo();
// 上传图片
const onUploadImg = async (files: File[], callback) => {
  const res = await Promise.all(
    files.map(async (file) => {
      return reqUpload(file);
    })
  );
  callback(res.map((item) => item.data.fileUrl));
};
const formState = reactive<BlogPub>({
  title: '',
  content: '',
  cover: null,
  letters: 0,
  readTime: 0,
  isReview: true,
  isPublish: false,
  isCommend: true,
  isTop: false,
  createTime: null,
  publishTime: null,
  updateTime: null,
  tags: [],
  categoryId: 1
});
const formRef = ref();
const onFinish = async (values: BlogPub) => {
  values.letters = values.content.length;
  values.readTime = Math.ceil(values.letters / 200);
  let res: BlogRes;
  // 发布
  if (!$route.query.id) {
    values.isPublish = true;
    values.createTime = new Date();
    values.publishTime = new Date();
    values.updateTime = new Date();
    res = await reqBlogPub(values);
  } else {
    values.updateTime = new Date();
    res = await reqBlogUpdate(Number($route.query.id), values);
  }
  if (res.code == 200) {
    $site.getBlogsInfo();
    $site.getBlogsAdInfo();
    $site.getBlogsArcInfo();
    message.success(res.message);
    if (!$route.query.id) $router.push('/adm/blog/list');
  } else {
    message.error(res.message);
  }
};
const onFinishFailed = (errorInfo: any) => {
  console.log(errorInfo);
};
// 保存
const onSave = async () => {
  formRef.value
    .validate()
    .then(async () => {
      formState.letters = formState.content.length;
      formState.readTime = Math.ceil(formState.letters / 200);
      formState.createTime = new Date();
      formState.updateTime = new Date();
      const res = await reqBlogPub(formState);
      if (res.code == 200) {
        $site.getBlogsInfo();
        $site.getBlogsAdInfo();
        $site.getBlogsArcInfo();
        message.success(res.message);
        $router.push('/adm/blog/list');
      } else {
        message.error(res.message);
      }
    })
    .catch((error) => {
      console.log('error', error);
    });
};

// 重置
const resetForm = () => {
  formRef.value?.resetFields();
};

// 分类
const categories = storeToRefs($site).categoriesInfo;
const catOptions = computed(() => {
  return categories.value?.map((item) => {
    return {
      value: item.id,
      label: item.title
    };
  });
});
// 标签
const tagOptions = storeToRefs($site).tagsInfo;
const filterOption = (input: string, option: any) => {
  return option.title.toLowerCase().indexOf(input.toLowerCase()) >= 0;
};

// 封面
const getCover = (file: File) => {
  formState.cover = file;
};

watchEffect(async () => {
  if ($route.query.id) {
    const res = await reqBlogById(Number($route.query.id));
    if (res.code === 200) {
      Object.keys(res.data.blog).forEach((key) => {
        if (key !== 'coverUrl') formState[key] = res.data.blog[key];
      });
      $site.getFileListInfo(res.data.blog.coverUrl);
    }
  }
});
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
  }
}
</style>
