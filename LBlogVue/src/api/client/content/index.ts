// layout的内容的接口
import client from '@/utils/requestClient';
import type { tagsRes } from './type';
import { useStore } from '@/store';
const $store = useStore();
enum API {
  TAGS_URL = 'content/tags',
  RANDOM_BLOS_URL = 'content/random_blogs'
}

export const reqTags = () => {
  return client.get<any, tagsRes>(API.TAGS_URL);
};
