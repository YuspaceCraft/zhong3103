import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Home from '@/components/Home'
import Register from '@/components/Register'
import GoodsItem from '@/components/GoodsItem'
import Order from '@/components/Order'
import Goodsadd from '@/components/Goodsadd'
import User from '@/components/User'
import Comment from '@/components/Comment'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      children: [
        {
          path: '/goodsitem',
          component: GoodsItem
        }, 
        {
          path: '/order',
          component: Order
        }, 
        {
          path: '/goodsadd',
          component: Goodsadd
        }, 
        {
          path: '/user',
          component: User
        }, 
        {
          path: '/comment',
          component: Comment
        },
      ]
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
  
    
  ]
})
