import Vue from 'vue'
import Router from 'vue-router'
//引入组件
import Menu from './components/Menu'
import CurrentOrder from './components/CurrentOrder'
import Bill from './components/Bill'
import DishSetter from './components/DishSetter'
import HomePage from './components/HomePage'

Vue.use(Router);
export default new Router({
    routes: [
        {path: '/Menu', component: Menu},
        {path: '/CurrentOrder', component: CurrentOrder},
        {path: '/Bill', component: Bill},
        {path: '/DishSetter', component: DishSetter},
        {path: '/', component: HomePage},
    ],
    mode: 'history'
})