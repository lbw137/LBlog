import admin from '@/utils/requestAdmin';
import {
  BlogEditRes,
  BlogListAdRes,
  BlogPub,
  BlogRes,
  BlogUpdate
} from './type';

enum API {
  BLOGS_URL = 'blogs',
  BLOGS_LIST_URL = 'blogs/list',
  BLOGS_DELETE_URL = 'blogs/delete',
  BLOGS_UPDATE_URL = 'blogs/update',
  BLOGS_PUBLISH_URL = 'blogs/publish',
  BLOGS_PUT_ISPUBLISH_URL = 'blogs/ispublish'
}

export const reqBlogListAd = () => {
  return admin.get<any, BlogListAdRes>(API.BLOGS_LIST_URL);
};
export const reqBlogById = (id: number) => {
  return admin.get<any, BlogEditRes>(API.BLOGS_URL, {
    params: { id }
  });
};

export const reqBlogUpdate = (id: number, blog: BlogUpdate) => {
  return admin.put<any, BlogRes>(API.BLOGS_UPDATE_URL + `/${id}`, blog, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
};

export const reqBlogDelete = (id: number) => {
  return admin.delete<any, BlogRes>(API.BLOGS_DELETE_URL, {
    params: { id }
  });
};

export const reqBlogPub = (blog: BlogPub) => {
  return admin.post<any, BlogRes>(API.BLOGS_PUBLISH_URL, blog, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
};

export const reqBlogPutIsPublish = (id: number, isPublish: boolean) => {
  return admin.put<any, BlogRes>(
    API.BLOGS_PUT_ISPUBLISH_URL + `/${id}`,
    isPublish,
    {
      headers: {
        'Content-Type': 'application/json'
      }
    }
  );
};
