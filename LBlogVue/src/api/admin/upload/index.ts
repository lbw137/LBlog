import admin from '@/utils/requestAdmin';
import { BlogPublishImgRes } from './type';

enum API {
  UPLOAD_URL = 'upload'
}

export const reqUpload = (file: File) => {
  const form = new FormData();
  form.append('file', file);
  return admin.post<any, BlogPublishImgRes>(API.UPLOAD_URL, form, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
};
