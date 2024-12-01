import { createRouter, createWebHistory } from 'vue-router';
import Login from '../views/Login.vue';
import Layout from '@/Layout/index.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '',
      redirect: "/login"
    },
    {
      path: '/index',
      name: 'index',
      component: Layout,
      children:[{
        path:'/index',
        component:()=> import("@/views/index.vue")
      }]
    },
  ],
})

export default router
