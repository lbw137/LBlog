import axios from 'axios';
import { message } from 'ant-design-vue';
import { useStore } from '../store';
import { refreshToken, isRefreshRequest } from './refreshToken';
const $store = useStore();
let request = axios.create({
  // 基础路径
  baseURL: import.meta.env.VITE_APP_BASE_API,
  // 请求超时时间
  timeout: 5000
});

// 请求拦截器
request.interceptors.request.use((config) => {
  if (config.url !== '/user/login' && config.url != '/user/refreshToken') {
    // 添加短token
    config.headers.Authorization = 'Bearer ' + $store.access_token;
  }
  // 返回配置对象
  return config;
});

// 响应拦截器
request.interceptors.response.use(
  async (res) => {
    if (res.data.success) {
      // 如果是刷新token的请求，不提示消息
      if (!isRefreshRequest(res.config)) message.success(res.data.message);
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
        const resp = await request.request(res.config);
        return resp;
      } else {
        // 长token过期，跳转到登录页
        $store.access_token = '';
        $store.refresh_token = '';
      }
    } else {
      message.error(res.data.message);
    }
    // 返回简化数据
    return res.data;
  },
  (err) => {
    message.error(err.response.data.message);
    return Promise.reject(err);
  }
);

export default request;
