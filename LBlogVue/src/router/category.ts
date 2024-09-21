import { RouteRecordRaw } from 'vue-router';

const category: RouteRecordRaw[] = [
  {
    path: '/category/studyNotes',
    component: () => import('@/pages/category/index.vue'),
    meta: {
      title: '学习笔记',
      color: '#44deff'
    }
  },
  {
    path: '/category/lifeDiary',
    component: () => import('@/pages/category/index.vue'),
    meta: {
      title: '生活日记',
      color: '#faec2d'
    }
  }
];

export default category;
