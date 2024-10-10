// 网站的标签与分类等的接口
import client from '@/utils/requestClient';
import type { CategoryRes, TagRes } from './type';
enum API {
  TAGS_URL = '/tags',
  CATEGORIES_URL = '/categories'
}

export const reqTags = () => {
  return client.get<any, TagRes>(API.TAGS_URL);
};

export const reqCategories = () => {
  return client.get<any, CategoryRes>(API.CATEGORIES_URL);
};
