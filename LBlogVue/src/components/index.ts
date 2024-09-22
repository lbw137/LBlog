import { App } from 'vue';
import LArticle from './L-Article/index.vue';
import LTag from './L-Tag/index.vue';
import LButton from './L-Button/index.vue';
import LIcon from './L-Icon/index.vue';
import LTimeline from './L-Timeline/index.vue';
import LMouse from './L-Mouse/index.vue';
import LLogo from './L-Logo/index.vue';
const globalComponent = {
  LArticle,
  LTag,
  LButton,
  LIcon,
  LTimeline,
  LMouse,
  LLogo
};

export default {
  install(app: App) {
    // 遍历 globalComponent 对象并注册每个组件
    Object.entries(globalComponent).forEach(([name, component]) => {
      app.component(name, component);
    });
  }
};
