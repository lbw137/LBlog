// 博客的接口
import client from '@/utils/requestClient';
import type { BlogArcRes, BlogDetailRes, blogListRes } from './type';
enum API {
  BLOGS_HOME_URL = 'blogs/home',
  BLOGS_TAG_URL = 'blogs/tag',
  BLOGS_CATEGORY_URL = 'blogs/category',
  BLOGS_DETAIL_URL = 'blogs/detail',
  BLOGS_RANDOM_URL = 'blogs/random',
  BLOGS_ARCHIVE_URL = 'blogs/archivist'
}
export const reqHomeBlogs = () => {
  return client.get<any, blogListRes>(API.BLOGS_HOME_URL);
};
export const reqTagBlogs = (id: number) => {
  return client.get<any, blogListRes>(API.BLOGS_TAG_URL, {
    params: {
      id
    }
  });
};
export const reqCatBlogs = (id: number) => {
  return client.get<any, blogListRes>(API.BLOGS_CATEGORY_URL, {
    params: {
      id
    }
  });
};

export const reqDetBlog = (id: number) => {
  return client.get<any, BlogDetailRes>(API.BLOGS_DETAIL_URL, {
    params: {
      id
    }
  });
};

export const reqArcBlogs = () => {
  return client.get<any, BlogArcRes>(API.BLOGS_ARCHIVE_URL);
};
