<template>
  <div class="blog-list">
    <a-card>
      <div class="blog-list-header">
        <span>博客列表</span>
        <div class="blog-tools">
          <a-radio-group v-model:value="radioOption">
            <a-radio-button value="all">全部</a-radio-button>
            <a-radio-button value="pubed">已发布</a-radio-button>
            <a-radio-button value="unpub">未发布</a-radio-button>
          </a-radio-group>
          <a-input-search
            placeholder="请输入文章标题"
            size="large"
            style="width: 50%"
            @search=""
          />
        </div>
      </div>
      <div class="blog-list-content">
        <a-table
          :columns="columns"
          :data-source="filterData"
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
            <template v-if="column.key === 'createTime'">
              {{ dayjs(record.createTime).format('YYYY-MM-DD HH:mm:ss') }}
            </template>
            <template v-if="column.key === 'updateTime'">
              {{ dayjs(record.updateTime).format('YYYY-MM-DD HH:mm:ss') }}
            </template>
            <template v-if="column.key === 'category'">
              <a-tag :color="record.category?.color">
                {{ record.category?.title }}
              </a-tag>
            </template>
            <template v-if="column.key === 'isPublish'">
              <a-switch v-model:checked="record.isPublish"></a-switch>
            </template>
            <template v-if="column.key === 'action'">
              <a-tooltip title="编辑" color="blue">
                <a-button
                  type="primary"
                  shape="circle"
                  style="margin-right: 1rem"
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
import { ref, computed } from 'vue';
import { message } from 'ant-design-vue';
import { useSiteInfo } from '@/store/useSiteInfo';
import { storeToRefs } from 'pinia';
import dayjs from 'dayjs';
const $site = useSiteInfo();
const confirm = (record: any) => {
  console.log(record);
  data.value.splice(data.value.indexOf(record), 1);
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
const radioOption = ref('all');
const columns = [
  {
    name: 'ID',
    dataIndex: 'id',
    key: 'id'
  },
  {
    title: '文章标题',
    dataIndex: 'title',
    key: 'title'
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    key: 'createTime'
  },
  {
    title: '更新时间',
    dataIndex: 'updateTime',
    key: 'updateTime'
  },
  {
    title: '所属分类',
    dataIndex: 'category',
    key: 'category'
  },
  {
    title: '是否发布',
    dataIndex: 'isPublish',
    key: 'isPublish'
  },
  {
    title: '操作',
    key: 'action'
  }
];

const data = storeToRefs($site).blogsAdInfo;
if (data.value.length === 0) {
  $site.getBlogsAdInfo();
}
const filterData = computed(() => {
  if (radioOption.value === 'all') {
    return data.value;
  } else if (radioOption.value === 'pubed') {
    return data.value.filter((item: any) => {
      return item.isPublish === true;
    });
  } else if (radioOption.value === 'unpub') {
    return data.value.filter((item: any) => {
      return item.isPublish === false;
    });
  }
});

// 分页
const current = ref(1);
const pageSize = ref(3);
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
.blog-list {
  display: flex;
  justify-content: center;
  padding: 2rem 0;
  .ant-card {
    width: 90%;
    &:hover {
      box-shadow: none !important;
    }
    .blog-list-header {
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
      .blog-tools {
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
    .blog-list-content {
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
  .blog-list {
    padding: 0;
  }
  .ant-card {
    width: 100% !important;
  }
  .ant-radio-group {
    margin-right: 0 !important;
  }
}
</style>
