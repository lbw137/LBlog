<template>
  <div class="category-list">
    <a-card>
      <div class="category-list-header">
        <span>分类列表</span>
        <div class="category-tools">
          <a-input-search
            placeholder="请输入分类标题"
            size="large"
            style="width: 50%"
            @search=""
          />
        </div>
      </div>
      <div class="category-list-content">
        <a-table
          :columns="columns"
          :data-source="categoryList"
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
                @cancel="cancel"
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
import { ref, computed, onMounted } from 'vue';
import { message } from 'ant-design-vue';
import type { Category } from '@/api/client/siteInfo/type';
import { useRouter } from 'vue-router';
import { reqCategories } from '@/api/client/siteInfo';
const $router = useRouter();
const onEdit = (record: any) => {
  $router.push({
    path: '/adm/category/pub',
    query: {
      id: record.id,
      title: record.title,
      color: record.color
    }
  });
};

const confirm = (record: any) => {
  console.log(record);
  categoryList.value.splice(categoryList.value.indexOf(record), 1);
  message.success({
    content: '删除成功',
    style: {
      marginTop: '10vh'
    }
  });
};

const cancel = (e: MouseEvent) => {
  console.log(e);
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
const categoryList = ref<Category[]>([]);
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
onMounted(async () => {
  const res = await reqCategories();
  console.log(666);
  if (res.code === 200) {
    categoryList.value = res.data.categories;
  }
});
</script>

<style scoped lang="scss">
.category-list {
  display: flex;
  justify-content: center;
  padding: 2rem 0;
  .ant-card {
    width: 90%;
    &:hover {
      box-shadow: none !important;
    }
    .category-list-header {
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
      .category-tools {
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
    .category-list-content {
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
  .category-list {
    padding: 0;
  }
  .ant-card {
    width: 100% !important;
  }
}
</style>
