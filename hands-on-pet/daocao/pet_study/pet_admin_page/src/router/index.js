import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'

const constRouter = [
  {
    path: '/login',
    name: 'login',
    component: Login,
  },
  {
    path: '',
    redirect: "/login"
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: constRouter,
})

export default router
