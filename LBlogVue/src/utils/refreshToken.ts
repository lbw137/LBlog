import { InternalAxiosRequestConfig } from 'axios';
import { reqRefreshToken } from '@/api/admin/user';
let promise: Promise<boolean> | null = null;
export async function refreshToken() {
  if (promise) return promise;
  promise = new Promise(async (resolve) => {
    const res = await reqRefreshToken();
    resolve(res.code === 200);
  });
  promise.finally(() => {
    promise = null;
  });
  return promise;
}

export function isRefreshRequest(config: InternalAxiosRequestConfig) {
  return config.headers && config.headers.__isRefreshToken;
}
