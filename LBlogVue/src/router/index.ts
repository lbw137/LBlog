import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import categories from './category';
import admins from './admin';
import {
  HomeOutlined,
  BulbOutlined,
  MailOutlined,
  MessageOutlined,
  SmileOutlined,
  ManOutlined,
  SettingOutlined
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
        name: 'archivist',
        component: () => import('@/pages/archivist/index.vue'),
        meta: {
          title: '归档',
          icon: MailOutlined
        }
      },
      {
        path: '/moments',
        name: 'moments',
        component: () => import('@/pages/moments/index.vue'),
        meta: {
          title: '动态',
          icon: MessageOutlined
        }
      },
      {
        path: '/friends',
        name: 'friends',
        component: () => import('@/pages/friends/index.vue'),
        meta: {
          title: '友链',
          icon: SmileOutlined
        }
      },
      {
        path: '/about',
        name: 'about',
        component: () => import('@/pages/about/index.vue'),
        meta: {
          title: '关于我',
          icon: ManOutlined
        }
      },
      {
        path: '/details/:id',
        name: 'details',
        component: () => import('@/pages/details/index.vue'),
        meta: {
          isHidden: true
        }
      }
    ]
  },
  {
    path: '/admin',
    name: 'admin',
    redirect: '/admin/blog',
    component: () => import('@/pages/admin/index.vue'),
    meta: {
      title: '管理',
      icon: SettingOutlined,
      isHidden: true
    },
    children: admins
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/pages/login/index.vue')
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach(async (to) => {
  if (to.name === 'login') return true;
  if (to.path.includes('admin')) {
    if (!localStorage.getItem('token')) return { name: 'login' };
    return true;
  }
});

export default router;
