import Vue from 'vue'
import App from './App.vue'
import router from './router'
import  Axios from 'axios'
import VueAxios from 'vue-axios'

import 'jquery/dist/jquery.min'
import 'popper.js/dist/umd/popper.min'
import 'bootstrap/dist/js/bootstrap.min'
import 'bootstrap/dist/css/bootstrap.min.css'
Vue.config.productionTip = false

Vue.use(VueAxios, Axios)
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
