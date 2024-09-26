import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import categories from './category';
import {
  HomeOutlined,
  BulbOutlined,
  MailOutlined,
  MessageOutlined,
  SmileOutlined,
  ManOutlined
} from '@ant-design/icons-vue';
export const routes: RouteRecordRaw[] = [
  {
    path: '/',
    redirect: '/home',
    component: () => import('@/layout/index.vue'),
    children: [
      {
        path: '/home',
        name: 'home',
        component: () => import('@/pages/home/index.vue'),
        meta: {
          title: '首页',
          icon: HomeOutlined
        }
      },
      {
        path: '/category',
        name: 'category',
        redirect: '/category/studyNotes',
        meta: {
          title: '分类',
          icon: BulbOutlined
        },
        children: categories
      },
      {
        path: '/archivist',
        component: () => import('@/pages/archivist/index.vue'),
        meta: {
          title: '归档',
          icon: MailOutlined
        }
      },
      {
        path: '/moments',
        component: () => import('@/pages/moments/index.vue'),
        meta: {
          title: '动态',
          icon: MessageOutlined
        }
      },
      {
        path: '/friends',
        component: () => import('@/pages/friends/index.vue'),
        meta: {
          title: '友链',
          icon: SmileOutlined
        }
      },
      {
        path: '/about',
        component: () => import('@/pages/about/index.vue'),
        meta: {
          title: '关于我',
          icon: ManOutlined
        }
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
