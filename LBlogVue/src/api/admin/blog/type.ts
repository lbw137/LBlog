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

// 博客发布类型定义
export interface BlogPub {
  title: string; // 标题
  content: string; // 内容
  cover: File | null; // 封面
  letters: number; // 字数
  readTime: number; // 阅读时间
  isReview: boolean; // 评论
  isPublish: boolean; // 发布
  isCommend: boolean; // 赞赏
  isTop: boolean; // 置顶
  createTime: Date | null; // 创建时间
  publishTime: Date | null; // 发布时间
  updateTime: Date | null; // 更新时间
  tags: number[]; // 标签
  categoryId: number; // 分类
}

// 博客编辑类型定义
export interface BlogEdit {
  title: string;
  content: string;
  coverUrl: string;
  isReview: boolean;
  isPublish: boolean;
  isCommend: boolean;
  isTop: boolean;
  tags: number[];
  categoryId: number;
}
// 博客编辑返回接口定义
export interface BlogEditRes {
  code: number;
  message: string;
  data: {
    blog: BlogEdit;
  };
}

// 博客更新类型定义
export interface BlogUpdate {
  title: string;
  content: string;
  cover: File | null;
  isReview: boolean;
  isPublish: boolean;
  isCommend: boolean;
  isTop: boolean;
  tags: number[];
  categoryId: number;
}

// 博客返回接口定义
export interface BlogRes {
  code: number;
  message: string;
}
