import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Home from '@/components/Home'
import Register from '@/components/Register'
import GoodsItem from '@/components/GoodsItem'
import GoodsInfo from '@/components/GoodsInfo'
import CartPage from '@/components/CartPage'
import Cart from '@/components/Cart'
import UserPage from '@/components/UserPage'
import User from '@/components/User'
import CommentPage from '@/components/CommentPage'
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
          path: 'goodsitem',
          component: GoodsItem
        },  
      ]
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/cartpage',
      name: 'CartPage',
      component: CartPage,
      children: [
        {
          path: 'cart',
          component: Cart
        },  
      ]
    },
    {
      path: '/commentpage',
      name: 'CommentPage',
      component: CommentPage,
      children: [
        {
          path: 'comment',
          component: Comment
        },  
      ]
    },
    {
      path: '/userpage',
      name: 'UserPage',
      component: UserPage,
      children: [
        {
          path: 'user',
          component: User
        },  
      ]
    },
    {
      path: '/goodsInfo',
      name: 'GoodsInfo',
      component: GoodsInfo
    },
    
  ]
})
