// 博客发布图片上传接口返回类型定义
export interface BlogPublishImgRes {
  code: number;
  message: string;
  data: {
    fileUrl: string;
  };
}
