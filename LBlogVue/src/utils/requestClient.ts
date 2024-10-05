import axios from 'axios';
import { useStore } from '@/store';
const $store = useStore();
let admin = axios.create({
  // 基础路径
  baseURL: import.meta.env.VITE_APP_CLIENT_API,
  // 请求超时时间
  timeout: 5000
});

// 请求拦截器
admin.interceptors.request.use((config) => {
  // 返回配置对象
  return config;
});

// 响应拦截器
admin.interceptors.response.use(
  async (res) => {
    // 返回简化数据
    return res.data;
  },
  (err) => {
    return Promise.reject(err);
  }
);

export default admin;
