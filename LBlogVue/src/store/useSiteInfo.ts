import { reqCategories, reqTags } from '@/api/client/siteInfo';
import { SubMenuType } from 'antd/es/menu/interface';
import { routes } from '@/router';
import { MenuProps, UploadProps } from 'ant-design-vue';
import { defineStore } from 'pinia';
import { ref, h } from 'vue';
import { Category, Tag } from '@/api/client/siteInfo/type';
import { BlogArcInfo, BlogList } from '@/api/client/blog/type';
import { reqArcBlogs, reqHomeBlogs } from '@/api/client/blog';
import { BlogListAd } from '@/api/admin/blog/type';
import { reqBlogListAd } from '@/api/admin/blog';
// 使用组合式方法
export const useSiteInfo = defineStore('SiteStore', () => {
  const getSiteInfo = async () => {
    getBlogsInfo();
    getMenuInfo();
    getTagsInfo();
  };
  /**
   * @description: 获取菜单信息与分类信息
   */
  const menuInfo = ref<MenuProps['items']>([]);
  const categoriesInfo = ref<Category[]>([]);
  const getCatInfo = async () => {
    const res = await reqCategories();
    if (res.code === 200) {
      categoriesInfo.value = res.data.categories;
    }
  };
  const getMenuCatInfo = async () => {
    await getCatInfo();
    if (
      menuInfo.value &&
      menuInfo.value.length > 1 &&
      categoriesInfo.value.length > 0
    ) {
      (menuInfo.value[1] as SubMenuType).children = categoriesInfo.value.map(
        (i) => ({
          key: `/category/${i.title}?id=${i.id}&color=${i.color}`.replace(
            '#',
            '%23'
          ),
          label: i.title
        })
      );
    }
  };
  const getMenuInfo = async () => {
    const items = ref<MenuProps['items']>([]);
    routes[0].children?.forEach(async (item) => {
      if (item.meta?.isHidden) return;
      items.value?.push({
        key: item.path,
        label: item.meta?.title,
        icon: () => h(item.meta?.icon as any) as any,
        children: item.children?.map((i) => ({
          key: i.path,
          label: i.meta?.title
        }))
      });
    });
    items.value?.push({
      key: routes[1].path,
      label: routes[1].meta?.title,
      icon: () => h(routes[1].meta?.icon as any) as any,
      children: routes[1].children?.map((i) => ({
        key: i.path,
        label: i.meta?.title
      }))
    });
    menuInfo.value = items.value;
    getMenuCatInfo();
  };

  /**
   * @description: 获取博客信息
   */
  // 用于博客首页显示，由于博客发布后，需要重新获取数据，其它博客请求都是路由切换自动发送的，所以不需要重新获取
  const blogsInfo = ref<BlogList[]>([]);
  const getBlogsInfo = async () => {
    const res = await reqHomeBlogs();
    if (res.code === 200) {
      blogsInfo.value = res.data.blogs;
    }
  };
  // 博客归档接口同理
  const blogsArcInfo = ref<BlogArcInfo>();
  const getBlogsArcInfo = async () => {
    const res = await reqArcBlogs();
    if (res.code === 200) {
      blogsArcInfo.value = res.data;
    }
  };

  /**
   * @description: 获取标签信息
   */
  const tagsInfo = ref<Tag[]>([]);
  const getTagsInfo = async () => {
    const res = await reqTags();
    if (res.code === 200) {
      tagsInfo.value = res.data.tags;
    }
  };

  /**
   * @description: 获取管理端博客列表信息
   */
  const blogsAdInfo = ref<BlogListAd[]>([]);
  const getBlogsAdInfo = async () => {
    const res = await reqBlogListAd();
    if (res.code === 200) {
      blogsAdInfo.value = res.data.blogs;
    }
  };

  /**
   * @description: 获取编辑博客时的封面信息
   */
  const fileListInfo = ref<UploadProps['fileList']>([]);
  const getFileListInfo = async (coverUrl: string) => {
    fileListInfo.value = [
      {
        uid: '-1',
        name: 'image.png',
        status: 'done',
        url: coverUrl
      }
    ];
  };
  const resetFileListInfo = () => {
    fileListInfo.value = [];
  };
  return {
    getSiteInfo,
    // 菜单和分类信息
    menuInfo,
    categoriesInfo,
    getCatInfo,
    getMenuInfo,
    getMenuCatInfo,
    // 博客信息
    blogsInfo,
    getBlogsInfo,
    blogsArcInfo,
    getBlogsArcInfo,
    // 标签信息
    tagsInfo,
    getTagsInfo,
    // 博客管理端信息
    blogsAdInfo,
    getBlogsAdInfo,
    // 编辑博客时的封面信息
    fileListInfo,
    getFileListInfo,
    resetFileListInfo
  };
});
