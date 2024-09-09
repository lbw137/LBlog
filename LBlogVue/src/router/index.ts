import {createRouter,createWebHistory,RouteRecordRaw} from 'vue-router'
import { MailOutlined, AppstoreOutlined } from '@ant-design/icons-vue';
export const routes:RouteRecordRaw[] = [
    {
        path:'/',
        redirect:'/home',
        component:()=>import('@/layout/index.vue'),
        children:[
            {
                path:'/home',
                component: ()=>import('@/pages/home/index.vue'),
                meta:{
                    title:'首页',
                    icon:MailOutlined
                }
            },
            {
                path:'/category',
                component: ()=>import('@/pages/category/index.vue'),
                meta:{
                    title:'分类',
                    icon:AppstoreOutlined
                }
            },
            {
                path:'/archivist',
                component: ()=>import('@/pages/archivist/index.vue'),
                meta:{
                    title:'归档',
                    icon:AppstoreOutlined
                }
            },
            {
                path:'/dynamic',
                component: ()=>import('@/pages/dynamic/index.vue'),
                meta:{
                    title:'动态',
                    icon:AppstoreOutlined
                }
            },
            {
                path:'/friends',
                component: ()=>import('@/pages/friends/index.vue'),
                meta:{
                    title:'友链',
                    icon:AppstoreOutlined
                }
            },
            {
                path:'/about',
                component: ()=>import('@/pages/about/index.vue'),
                meta:{
                    title:'关于我',
                    icon:AppstoreOutlined
                }
            }
        ]
    },
]

const router = createRouter({
    history:createWebHistory(),
    routes
})

//路由跳转后缓慢滚动至顶部
const smoothScrollToTop = (duration = 300) =>{  
    const start = window.scrollY;  
    const startTime = performance.now();  
    const animateScroll = (currentTime:number) => {  
      const timeElapsed = currentTime - startTime;  
      const progress = Math.min(timeElapsed / duration, 1);  
      const easeInOut = 0.5 - Math.cos(progress * Math.PI) / 2;  

      window.scrollTo(0, start * (1 - easeInOut));  

      if (timeElapsed < duration) {  
        requestAnimationFrame(animateScroll);  
      }  
    };  
    requestAnimationFrame(animateScroll);  
}

router.afterEach(() => {
    smoothScrollToTop()
});
export default router