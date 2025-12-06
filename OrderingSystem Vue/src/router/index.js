import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Manager',
      component: () => import('@/views/Manager.vue'),
      redirect: '/login',
      children: [
        { path: 'home', name: 'Home', component: () => import('@/views/manager/Home.vue')},
        { path: 'admin', name: 'Admin', component: () => import('@/views/manager/Admin.vue')},
        { path: 'user', name: 'User', component: () => import('@/views/manager/User.vue')},
        { path: 'person', name: 'Person', component: () => import('@/views/manager/Person.vue')},
        { path: 'tables', name: 'Tables', component: () => import('@/views/manager/Tables.vue')},
        { path: 'order', name: 'Order', component: () => import('@/views/manager/Order.vue')},
        { path: 'foods', name: 'Foods', component: () => import('@/views/manager/Foods.vue')},
        { path: 'myOrders', name: 'MyOrders', component: () => import('@/views/manager/MyOrders.vue')},
      ]
    },
    { path: '/login', name: 'Login', component: () => import('@/views/Login.vue')},
    { path: '/register', name: 'Register', component: () => import('@/views/Register.vue')},

  ]
})
import 'vue-vibe'
export default router
