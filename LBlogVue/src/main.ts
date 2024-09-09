import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'
import Antd from 'ant-design-vue';
import { createPinia } from 'pinia'
const pinia = createPinia()

const app = createApp(App)
app.use(router).use(Antd).use(pinia)
app.mount('#app')
