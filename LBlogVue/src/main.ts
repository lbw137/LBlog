import { createApp } from 'vue';
import './assets/styles/index.scss';
import App from './App.vue';
import router from './router';
import Antd from 'ant-design-vue';
import { createPinia } from 'pinia';
import GlobalComponents from './components';
import './assets/icon/iconfont.js';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate';
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

const app = createApp(App);
app.use(router).use(Antd).use(pinia).use(GlobalComponents);
app.mount('#app');
