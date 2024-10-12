import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
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
import { useStore } from '@/store';

export const routes: RouteRecordRaw[] = [
  {
    path: '/',
    redirect: '/home',
    component: () => import('@/layout/index.vue'),
    children: [
      {
        path: '/home',
        name: 'home',
        component: () => import('@/pages/client/home/index.vue'),
        meta: {
          title: '首页',
          icon: HomeOutlined
        }
      },
      {
        path: '/category/:title',
        name: 'category',
        component: () => import('@/pages/client/category/index.vue'),
        meta: {
          title: '分类',
          icon: BulbOutlined
        }
      },
      {
        path: '/tag/:title',
        name: 'tag',
        component: () => import('@/pages/client/tag/index.vue'),
        meta: {
          title: '标签',
          isHidden: true
        }
      },
      {
        path: '/archivist',
        name: 'archivist',
        component: () => import('@/pages/client/archivist/index.vue'),
        meta: {
          title: '归档',
          icon: MailOutlined
        }
      },
      {
        path: '/moments',
        name: 'moments',
        component: () => import('@/pages/client/moments/index.vue'),
        meta: {
          title: '动态',
          icon: MessageOutlined
        }
      },
      {
        path: '/friends',
        name: 'friends',
        component: () => import('@/pages/client/friends/index.vue'),
        meta: {
          title: '友链',
          icon: SmileOutlined
        }
      },
      {
        path: '/about',
        name: 'about',
        component: () => import('@/pages/client/about/index.vue'),
        meta: {
          title: '关于我',
          icon: ManOutlined
        }
      },
      {
        path: '/detail/:id',
        name: 'detail',
        component: () => import('@/pages/client/detail/index.vue'),
        meta: {
          isHidden: true
        }
      }
    ]
  },
  {
    path: '/adm',
    name: 'admin',
    redirect: '/adm/blog',
    component: () => import('@/pages/admin/index.vue'),
    meta: {
      title: '管理',
      icon: SettingOutlined
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
  routes,
  scrollBehavior(to, from, savedPosition) {
    // 始终滚动到顶部
    return { top: 0, behavior: 'smooth' };
  }
});

router.beforeEach(async (to) => {
  const $store = useStore();
  if (to.name === 'login') return true;
  if (to.path.includes('admin')) {
    if (!$store.refresh_token) return { name: 'login' };
    return true;
  }
});

export default router;
