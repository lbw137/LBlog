<template>
  <div class="tag-pub">
    <a-card>
      <div class="tag-pub-header">
        <span>分类发布</span>
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
          :rules="[{ required: true, message: '请输入分类标题' }]"
        >
          <a-input v-model:value="formState.title" />
        </a-form-item>
        <!-- 颜色 -->
        <a-form-item
          label="颜色"
          name="color"
          :rules="[{ required: true, message: '请选择分类颜色' }]"
        >
          <Chrome v-model="formState.color"></Chrome>
        </a-form-item>
        <!-- 按钮 -->
        <a-form-item :wrapper-col="{ span: 24 }">
          <a-row justify="end">
            <a-button type="primary" html-type="submit">
              {{ $route.query.id ? '更新' : '发布' }}
            </a-button>
            <a-button
              @click="resetForm"
              type="primary"
              danger
              style="margin: 0 4%"
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
import { reactive, ref, watchEffect } from 'vue';
import { Chrome } from '@ckpack/vue-color';
import { useRoute, useRouter } from 'vue-router';
import { CategoryRes } from '@/api/admin/category/type';
import { Category } from '@/api/client/siteInfo/type';
import { reqCatPub, reqCatUpd } from '@/api/admin/category';
import { useSiteInfo } from '@/store/useSiteInfo';
import { message } from 'ant-design-vue';
const $site = useSiteInfo();
const $route = useRoute();
const $router = useRouter();
interface FormState {
  title: string; // 标题
  color: any; // 颜色
}
const formState = reactive<FormState>({
  title: '',
  color: ''
});
const formRef = ref();
const onFinish = async (values: FormState) => {
  let res: CategoryRes;
  const tag: Category = {
    id: Number($route.query.id),
    ...values
  };
  if ($route.query.id) {
    // 更新
    res = await reqCatUpd(tag);
  } else {
    // 发布
    res = await reqCatPub(tag);
  }
  if (res.code === 200) {
    $site.getMenuCatInfo();
    $site.getBlogsInfo();
    message.success(res.message);
    $router.push('/adm/category/list');
  } else {
    message.error(res.message);
  }
};
const onFinishFailed = (errorInfo: any) => {
  console.log(errorInfo);
};
// 重置
const resetForm = () => {
  formRef.value?.resetFields();
};

let id = '0';
watchEffect(() => {
  if ($route.query.id && $route.query.id != id) {
    formState.title = $route.query.title as string;
    formState.color = $route.query.color as string;
    id = $route.query.id as string;
  }
  if ((typeof formState.color as Object) === 'object') {
    formState.color = formState.color.hex;
  }
  if (!$route.query.id) {
    formState.title = '';
    formState.color = '#000';
  }
});
</script>

<style scoped lang="scss">
.tag-pub {
  display: flex;
  justify-content: center;
  padding: 2rem 0;
  .ant-card {
    width: 90%;
    &:hover {
      box-shadow: none !important;
    }
    .tag-pub-header {
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
