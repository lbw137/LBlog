import admin from '@/utils/requestAdmin';
import { BlogListAdRes } from './type';

enum API {
  BLOGS_URL = 'blogs/list'
}

export const reqBlogListAd = () => {
  return admin.get<any, BlogListAdRes>(API.BLOGS_URL);
};
