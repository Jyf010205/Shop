import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },

  {
    path: '/product',
    component: Layout,
    redirect: '/product/list',
    name: 'Product',
    meta: { title: '商品', icon: 'product' },
    children: [
      {
        path: 'list',
        name: 'list',
        component: () => import('@/views/product/list'),
        meta: { title: '商品列表', icon: 'product-list' }
      },
      {
        path: 'add',
        name: 'add',
        component: () => import('@/views/product/add'),
        meta: { title: '添加商品', icon: 'product-add' }
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
