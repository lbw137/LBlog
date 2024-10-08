import { createApp } from 'vue';
import './assets/styles/index.scss';
import App from './App.vue';
import router from './router';
import Antd from 'ant-design-vue';
import { createPinia } from 'pinia';
import GlobalComponents from './components';
const pinia = createPinia();

const app = createApp(App);
app.use(router).use(Antd).use(pinia).use(GlobalComponents);
app.mount('#app');
