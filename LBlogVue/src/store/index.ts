import { defineStore } from 'pinia';
import { ref } from 'vue';
// 使用组合式方法
export const useStore = defineStore(
  'store',
  () => {
    const mouseEffect = ref(true);
    const access_token = ref('');
    const refresh_token = ref('');
    const loginTime = ref<Date>();
    const isExpired = () => {
      if (!loginTime.value) return true;
      // 如果值不是日期类型，则返回true
      if (typeof loginTime.value === 'string') {
        try {
          loginTime.value = new Date(loginTime.value);
        } catch (error) {
          return true;
        }
      }
      return (
        new Date().getTime() - loginTime.value.getTime() > 3600 * 24 * 1000
      );
    };
    return { mouseEffect, access_token, refresh_token, loginTime, isExpired };
  },
  {
    persist: {
      omit: []
    }
  }
);
