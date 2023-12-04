import { createApp } from 'vue';
import App from './App.vue';
import VueKonva from 'vue-konva';
import Vue3ColorPicker from "vue3-colorpicker";
import "vue3-colorpicker/style.css";

const app = createApp(App);
app.use(VueKonva);
app.use(Vue3ColorPicker);
app.mount('#app');