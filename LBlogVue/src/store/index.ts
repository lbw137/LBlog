import { defineStore } from 'pinia';
import { ref } from 'vue';
// 使用组合式方法
export const useStore = defineStore(
  'store',
  () => {
    const mouseEffect = ref(true);
    return { mouseEffect };
  },
  {
    persist: {
      omit: []
    }
  }
);
