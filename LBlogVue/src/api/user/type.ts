// 登陆接口的参数类型定义
export interface loginForm {
  username: string;
  password: string;
}

// 登录接口的返回类型定义
export interface loginRes {
  code: number;
  msg: string;
  success: boolean;
  data: {
    access_token: string;
    refresh_token: string;
  };
}
