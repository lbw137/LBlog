import admin from '@/utils/requestAdmin';
import { TagRes } from './type';
import { Tag } from '@/api/client/siteInfo/type';

enum API {
  // 获取标签列表使用客户端的接口
  TAGS_DELETE_URL = 'tags/delete',
  TAGS_UPDATE_URL = 'tags/update',
  TAGS_PUB_URL = 'tags/pub'
}

export const reqTagDel = (id: number) => {
  return admin.delete<any, TagRes>(API.TAGS_DELETE_URL, {
    params: { id }
  });
};

export const reqTagUpd = (tag: Tag) => {
  return admin.put<any, TagRes>(API.TAGS_UPDATE_URL, tag);
};

export const reqTagPub = (tag: Tag) => {
  return admin.post<any, TagRes>(API.TAGS_PUB_URL, tag);
};
