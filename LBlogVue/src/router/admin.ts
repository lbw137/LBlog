import { RouteRecordRaw } from 'vue-router';

const admin: RouteRecordRaw[] = [
  {
    path: '/admin/blog',
    name: 'adminBlog',
    redirect: '/admin/blog/list',
    meta: {
      title: '博客管理'
    },
    children: [
      {
        path: '/admin/blog/list',
        component: () => import('@/pages/admin/blog/list/index.vue')
      },
      {
        path: '/admin/blog/pub',
        component: () => import('@/pages/admin/blog/pub/index.vue')
      }
    ]
  },
  {
    path: '/admin/category',
    name: 'adminCategory',
    redirect: '/admin/category/list',
    meta: {
      title: '分类管理'
    },
    children: [
      {
        path: '/admin/category/list',
        component: () => import('@/pages/admin/category/list/index.vue')
      },
      {
        path: '/admin/category/pub',
        component: () => import('@/pages/admin/category/pub/index.vue')
      }
    ]
  },
  {
    path: '/admin/tag',
    name: 'adminTag',
    redirect: '/admin/tag/list',
    meta: {
      title: '标签管理'
    },
    children: [
      {
        path: '/admin/tag/list',
        component: () => import('@/pages/admin/tag/list/index.vue')
      },
      {
        path: '/admin/tag/pub',
        component: () => import('@/pages/admin/tag/pub/index.vue')
      }
    ]
  }
];

export default admin;
