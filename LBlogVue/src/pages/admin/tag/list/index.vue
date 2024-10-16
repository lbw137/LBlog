<template>
  <div class="tag-list">
    <a-card>
      <div class="tag-list-header">
        <span>标签列表</span>
        <div class="tag-tools">
          <a-input-search
            placeholder="请输入标签标题"
            size="large"
            style="width: 50%"
            v-model:value="inputValue"
            @search="onSearch"
          />
        </div>
      </div>
      <div class="tag-list-content">
        <a-table
          :columns="columns"
          :data-source="searchfilter"
          :pagination="Pagination"
          :scroll="{ x: true }"
        >
          <template #headerCell="{ column }">
            <template v-if="column.key === 'id'">
              <span>
                {{ column.name }}
              </span>
            </template>
          </template>

          <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'color'">
              <a-tag :color="record.color">{{ record.color }}</a-tag>
            </template>
            <template v-else-if="column.key === 'action'">
              <a-tooltip title="编辑" color="blue">
                <a-button
                  type="primary"
                  shape="circle"
                  style="margin-right: 1rem"
                  @click="onEdit(record)"
                >
                  <template #icon>
                    <l-icon icon="icon-edit"></l-icon>
                  </template>
                </a-button>
              </a-tooltip>
              <a-popconfirm
                title="您确定要删除吗?"
                ok-text="是"
                cancel-text="否"
                @confirm="confirm(record)"
              >
                <a-button type="primary" shape="circle" danger>
                  <template #icon>
                    <l-icon icon="icon-delete"></l-icon>
                  </template>
                </a-button>
              </a-popconfirm>
            </template>
          </template>
        </a-table>
      </div>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import { message } from 'ant-design-vue';
import { useRouter } from 'vue-router';
import { useSiteInfo } from '@/store/useSiteInfo';
import { storeToRefs } from 'pinia';
import { Tag } from '@/api/client/siteInfo/type';
import { reqTagDel } from '@/api/admin/tag';
const $site = useSiteInfo();
const $router = useRouter();
const onEdit = (record: Tag) => {
  $router.push({
    path: '/adm/tag/pub',
    query: {
      id: record.id,
      title: record.title,
      color: record.color
    }
  });
};

const confirm = async (record: Tag) => {
  const res = await reqTagDel(record.id);
  if (res.code === 200) {
    // 刷新标签列表
    $site.getTagsInfo();
    // 刷新博客列表
    $site.getBlogsInfo();
    message.success(res.message);
  }
};

const columns = [
  {
    name: 'ID',
    dataIndex: 'id',
    key: 'id'
  },
  {
    title: '标题',
    dataIndex: 'title',
    key: 'title'
  },
  {
    title: '颜色',
    dataIndex: 'color',
    key: 'color'
  },
  {
    title: '操作',
    key: 'action'
  }
];
const tagList = storeToRefs($site).tagsInfo;
const searchfilter = computed(() => {
  if (inputValue.value === '') {
    searchValue.value = '';
    return tagList.value;
  }
  return tagList.value.filter((item) => {
    if (item.title.toLowerCase().indexOf(searchValue.value.toLowerCase()) >= 0)
      return true;
    return false;
  });
});
// 用于在清空搜索框时，重置搜索结果
const inputValue = ref('');
// 按下搜索时，将输入的值赋值给searchValue
const searchValue = ref('');
const onSearch = (value: string) => {
  searchValue.value = value;
};

// 分页
const current = ref(1);
const pageSize = ref(5);
const Pagination = computed(() => ({
  onChange: (page: number) => {
    current.value = page;
  },
  pageSize: pageSize.value,
  current: current.value,
  showTotal: (total: number) => `共 ${total} 条记录`
}));
</script>

<style scoped lang="scss">
.tag-list {
  display: flex;
  justify-content: center;
  padding: 2rem 0;
  .ant-card {
    width: 90%;
    &:hover {
      box-shadow: none !important;
    }
    .tag-list-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 6.5rem;
      padding: 0 1%;
      border-bottom: 1px solid #e8e8e8;
      span {
        color: $logo-color;
        font-weight: bolder;
        font-size: 2.5rem;
        text-wrap: nowrap;
      }
      .tag-tools {
        display: flex;
        width: 50%;
        align-items: center;
        justify-content: flex-end;
        user-select: none;
        .ant-radio-group {
          margin-right: 2rem;
          text-wrap: nowrap;
        }
      }
    }
  }
}
:deep(.ant-table-pagination) {
  justify-content: center !important;
  margin-bottom: 2rem;
}

@media (max-width: $media-max-header) {
  .ant-input-search {
    display: none;
  }
  .tag-list {
    padding: 0;
  }
  .ant-card {
    width: 100% !important;
  }
}
</style>
