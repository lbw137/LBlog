// 用户接口
import admin from '@/utils/requestAdmin';
import type { loginForm, loginRes } from './type';
import { useStore } from '@/store';
const $store = useStore();
enum API {
  LOGIN_URL = 'user/login',
  REFRESH_TOKEN = 'user/refreshToken'
}
// 登陆接口
export const reqLogin = (data: loginForm) => {
  return admin.post<any, loginRes>(API.LOGIN_URL, data);
};
// 刷新短token接口
export const reqRefreshToken = () => {
  return admin.get<any, loginRes>(API.REFRESH_TOKEN, {
    headers: {
      Authorization: 'Bearer ' + $store.refresh_token,
      __isRefreshToken: true
    }
  });
};
