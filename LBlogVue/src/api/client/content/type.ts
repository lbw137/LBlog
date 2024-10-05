export interface Tags {
  id: number;
  title: string;
  color: string;
}

// 标签接口的返回类型定义
export interface tagsRes {
  code: number;
  msg: string;
  data: {
    tags: Tags[];
  };
}
