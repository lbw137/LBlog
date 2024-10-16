import admin from '@/utils/requestAdmin';
import { CategoryRes } from './type';
import { Category } from '@/api/client/siteInfo/type';

enum API {
  // 获取标签列表使用客户端的接口
  CAT_DELETE_URL = 'category/delete',
  CAT_UPDATE_URL = 'category/update',
  CAT_PUBLISH_URL = 'category/publish'
}

export const reqCatDel = (id: number) => {
  return admin.delete<any, CategoryRes>(API.CAT_DELETE_URL, {
    params: { id }
  });
};

export const reqCatUpd = (category: Category) => {
  return admin.put<any, CategoryRes>(API.CAT_UPDATE_URL, category);
};

export const reqCatPub = (category: Category) => {
  return admin.post<any, CategoryRes>(API.CAT_PUBLISH_URL, category);
};
