<template>
  <a-upload
    list-type="picture-card"
    :max-count="1"
    :before-upload="beforeUpload"
    v-model:file-list="fileList"
    @preview="handlePreview"
  >
    <div>
      <PlusOutlined />
      <div style="margin-top: 8px">Upload</div>
    </div>
  </a-upload>
  <a-modal
    :open="previewVisible"
    :title="previewTitle"
    :footer="null"
    @cancel="handleCancel"
  >
    <img alt="example" style="width: 100%" :src="previewImage" />
  </a-modal>
</template>

<script setup lang="ts">
import { onUnmounted, ref } from 'vue';
import { PlusOutlined } from '@ant-design/icons-vue';
import { useSiteInfo } from '@/store/useSiteInfo';
import { storeToRefs } from 'pinia';
const $site = useSiteInfo();

const fileList = storeToRefs($site).fileListInfo;
const previewVisible = ref(false);
const previewImage = ref('');
const previewTitle = ref('');
const emit = defineEmits(['update']);
const getBase64 = (file: File) => {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = (error) => reject(error);
  });
};

const beforeUpload = (file: File) => {
  emit('update', file);
  // 阻止默认的上传行为
  return false;
};

// 预览图片
const handlePreview = async (file: any) => {
  if (!file.url && !file.preview) {
    file.preview = (await getBase64(file.originFileObj)) as string;
  }
  previewImage.value = file.url || file.preview;
  previewVisible.value = true;
  previewTitle.value =
    file.name || file.url.substring(file.url.lastIndexOf('/') + 1);
  console.log(file);
};
// 关闭预览图片
const handleCancel = () => {
  previewVisible.value = false;
  previewTitle.value = '';
};
onUnmounted(() => {
  $site.fileListInfo = [];
});
</script>

<style scoped>
.avatar-uploader > .ant-upload {
  width: 128px;
  height: 128px;
}
.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}
</style>
