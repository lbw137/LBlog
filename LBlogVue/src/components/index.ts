import { App, DefineComponent } from 'vue';
const components: Record<
  string,
  DefineComponent<{}, {}, any>
> = import.meta.glob('./**/index.vue', { eager: true });
export default {
  install(app: App) {
    // 遍历 globalComponent 对象并注册每个组件
    Object.entries(components).forEach(([name, component]) => {
      const componentName = name.split('/')[1].replace('-', '');
      app.component(componentName, component.default);
    });
  }
};
