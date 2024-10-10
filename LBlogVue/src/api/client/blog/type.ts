import type { Category, Tag } from '../siteInfo/type';

// 博客类型定义
export interface Blog {
  id: number;
  title: string;
  content: string;
  cover: string;
  views: number;
  letters: number;
  readTime: number;
  isReview: boolean;
  isPublish: boolean;
  isCommend: boolean;
  isTop: boolean;
  createTime: Date;
  publishTime: Date;
  updateTime: Date;
  category: Category;
  tags: Tag[];
}
// 博客列表类型定义
export interface BlogList {
  id: number;
  title: string;
  cover: string;
  views: number;
  letters: number;
  readTime: number;
  isTop: boolean;
  createTime: Date;
  category: Category;
  tags: Tag[];
}
// 博客详情类型定义
export interface BlogDetail {
  id: number;
  title: string;
  content: string;
  views: number;
  letters: number;
  readTime: number;
  isReview: boolean;
  isCommend: boolean;
  isTop: boolean;
  createTime: Date;
  publishTime: Date;
  updateTime: Date;
  category: Category;
  tags: Tag[];
}
// 博客归档类型定义
export interface BlogArchive {
  id: number;
  title: string;
  createTime: Date;
}

// 博客首页接口的返回类型定义
export interface blogListRes {
  code: number;
  msg: string;
  data: {
    blogs: BlogList[];
  };
}
