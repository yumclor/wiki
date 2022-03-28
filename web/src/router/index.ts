import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/admin/user',
    name: '/Admin-User.vue',
    component: () => import('../views/admin/Admin-User.vue')
  },
  {
    path: '/admin/ebook',
    name: '/Admin-Ebook.vue',
    component: () => import('../views/admin/Admin-Ebook.vue')
  },
  {
    path: '/admin/category',
    name: '/Admin-Category.vue',
    component: () => import('../views/admin/Admin-Category.vue')
  },
  {
    path: '/admin/doc',
    name: '/Admin-Doc.vue',
    component: () => import('../views/admin/Admin-Doc.vue')
  },
  {
    path: '/doc',
    name: '/Doc.vue',
    component: () => import('../views/Doc.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
