import axios from 'axios';
import { message } from 'ant-design-vue';
import { useStore } from '@/store';
import { refreshToken, isRefreshRequest } from './refreshToken';
let admin = axios.create({
  // 基础路径
  baseURL: import.meta.env.VITE_APP_ADMIN_API,
  // 请求超时时间
  timeout: 5000
});

// 请求拦截器
admin.interceptors.request.use((config) => {
  const $store = useStore();
  if (config.url === 'user/login' || config.url === 'user/refreshToken') {
    return config;
  }
  // 添加短token
  config.headers.Authorization = 'Bearer ' + $store.access_token;
  // 返回配置对象
  return config;
});

// 响应拦截器
admin.interceptors.response.use(
  async (res) => {
    const $store = useStore();
    if (res.data.code === 200) {
      // 收集短token
      if (res.data.data.access_token) {
        $store.access_token = res.data.data.access_token;
      }
      // 收集长token
      if (res.data.data.refresh_token) {
        $store.refresh_token = res.data.data.refresh_token;
      }
    } else if (res.data.code == 401 && !isRefreshRequest(res.config)) {
      // 短token过期，用长token刷新短token
      const isSuccess = await refreshToken();
      if (isSuccess) {
        // 重新请求
        res.config.headers.Authorization = 'Bearer ' + $store.access_token;
        const resp = await admin.request(res.config);
        return resp;
      } else {
        // 长token过期，跳转到登录页
        $store.access_token = '';
        $store.refresh_token = '';
        window.location.href = '/login';
      }
    }
    // 返回简化数据
    return res.data;
  },
  (err) => {
    message.error(err.response.data.message);
    return Promise.reject(err);
  }
);

export default admin;
