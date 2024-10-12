import { Category } from '@/api/client/siteInfo/type';
// 博客列表类型定义
export interface BlogListAd {
  id: number;
  title: string;
  createTime: Date;
  updateTime: Date;
  category: Category;
  isPublish: boolean;
}
// 博客列表接口返回类型定义
export interface BlogListAdRes {
  code: number;
  message: string;
  data: {
    blogs: BlogListAd[];
  };
}
