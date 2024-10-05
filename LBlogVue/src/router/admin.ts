import { RouteRecordRaw } from 'vue-router';

const admin: RouteRecordRaw[] = [
  {
    path: '/adm/blog',
    name: 'adminBlog',
    redirect: '/adm/blog/list',
    meta: {
      title: '博客管理'
    },
    children: [
      {
        path: 'list',
        component: () => import('@/pages/admin/blog/list/index.vue')
      },
      {
        path: 'pub',
        component: () => import('@/pages/admin/blog/pub/index.vue')
      }
    ]
  },
  {
    path: '/adm/category',
    name: 'adminCategory',
    redirect: '/adm/category/list',
    meta: {
      title: '分类管理'
    },
    children: [
      {
        path: 'list',
        component: () => import('@/pages/admin/category/list/index.vue')
      },
      {
        path: 'pub',
        component: () => import('@/pages/admin/category/pub/index.vue')
      }
    ]
  },
  {
    path: '/adm/tag',
    name: 'adminTag',
    redirect: '/adm/tag/list',
    meta: {
      title: '标签管理'
    },
    children: [
      {
        path: 'list',
        component: () => import('@/pages/admin/tag/list/index.vue')
      },
      {
        path: 'pub',
        component: () => import('@/pages/admin/tag/pub/index.vue')
      }
    ]
  }
];

export default admin;
