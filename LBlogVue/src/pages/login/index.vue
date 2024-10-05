<template>
  <div class="contanier">
    <div class="con-avatar">
      <a-avatar src="src/assets/image/roxy.jpg" :size="150"></a-avatar>
    </div>
    <a-card>
      <a-form
        ref="formRef"
        :model="formState"
        name="basic"
        :wrapper-col="{ span: 24 }"
        autocomplete="off"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <a-form-item
          name="username"
          :rules="[{ required: true, message: '用户名不能为空！' }]"
        >
          <a-input
            v-model:value="formState.username"
            placeholder="请输入用户名"
          >
            <template #prefix>
              <l-icon icon="icon-user"></l-icon>
            </template>
          </a-input>
        </a-form-item>
        <a-form-item
          name="password"
          :rules="[{ required: true, message: '密码不能为空！' }]"
        >
          <a-input-password
            v-model:value="formState.password"
            placeholder="请输入密码"
          >
            <template #prefix>
              <l-icon icon="icon-lock"></l-icon>
            </template>
          </a-input-password>
        </a-form-item>
        <a-form-item style="transform: translateY(50%)">
          <a-row justify="center">
            <a-button
              type="primary"
              html-type="submit"
              style="margin-right: 2rem"
              :loading="loading"
              >登陆</a-button
            >
            <a-button @click="resetForm">重置</a-button>
          </a-row>
        </a-form-item>
      </a-form>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, Reactive } from 'vue';
import { useRouter } from 'vue-router';
import { reqLogin } from '@/api/admin/user';
import type { loginForm } from '@/api/admin/user/type';
const $router = useRouter();
const formRef = ref();
const loading = ref(false);
const formState: Reactive<loginForm> = reactive({
  username: '',
  password: ''
});
const onFinish = async (values: loginForm) => {
  loading.value = true;
  const res = await reqLogin(values);
  loading.value = false;
  if (res.code === 200) {
    $router.push('/admin');
  }
};
const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
const resetForm = () => {
  formRef.value?.resetFields();
};
</script>

<style scoped lang="scss">
.contanier {
  display: flex;
  position: relative;
  height: 100vh;
  background-color: #2b2b2b;
  justify-content: center;
  align-items: center;
  .ant-card {
    width: 50rem;
    height: 30rem;
    box-shadow: $big-shadow;
    display: flex;
    align-items: center;
    &:hover {
      box-shadow: $big-shadow !important;
    }
    :deep(.ant-card-body) {
      flex: 1;
      display: flex;
      justify-content: center;
    }
    .ant-form {
      width: 70%;
      margin-top: 15%;
    }
  }
  .con-avatar {
    position: absolute;
    z-index: 1;
    display: flex;
    width: 17rem;
    height: 17rem;
    border-radius: 50%;
    background-color: #fff;
    justify-content: center;
    align-items: center;
    transform: translateY(-90%);
    box-shadow: 0 0 10px #ddd;
    user-select: none;
    .ant-avatar {
      pointer-events: none;
    }
  }
}
</style>
