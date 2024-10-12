import { defineStore } from 'pinia';
import { ref } from 'vue';
// 使用组合式方法
export const useStore = defineStore(
  'store',
  () => {
    const mouseEffect = ref(true);
    const access_token = ref('');
    const refresh_token = ref('');

    return { mouseEffect, access_token, refresh_token };
  },
  {
    persist: {
      omit: []
    }
  }
);
