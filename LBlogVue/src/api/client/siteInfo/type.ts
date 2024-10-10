// 标签类型定义
export interface Tag {
  id: number;
  title: string;
  color: string;
}
// 标签接口返回类型定义
export interface TagRes {
  code: number;
  message: string;
  data: {
    tags: Tag[];
  };
}

// 分类类型定义
export interface Category {
  id: number;
  title: string;
  color: string;
}
// 分类接口返回类型定义
export interface CategoryRes {
  code: number;
  message: string;
  data: {
    categories: Category[];
  };
}
