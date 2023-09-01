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

//lsl
import LslGoodsItem from '@/components/LslGoodsItem'
import LslOrder from '@/components/LslOrder'
import LslGoodsadd from '@/components/LslGoodsadd'
import LslComment from '@/components/LslComment'
import LslHome from '@/components/LslHome'
import LslUser from '@/components/LslUser'

import MainPage from '@/components/MainPage'
import ZrzOrder from '@/components/ZrzOrder'
import Note from '@/components/Note';
import Seting from '@/components/Seting';
import OrdertoComment1 from '@/components/OrdertoComment1'

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
    //lsl
    {
      path: '/lslhome',
      name: 'LslHome',
      component: LslHome,
      children: [
        {
          path: '/lslgoodsitem',
          component: LslGoodsItem
        }, 
        {
          path: '/lslorder',
          component: LslOrder
        }, 
        {
          path: '/lslgoodsadd',
          component: LslGoodsadd
        }, 
        {
          path: '/lsluser',
          component: LslUser
        }, 
        {
          path: '/lslcomment',
          component: LslComment
        },
      ]
    },
    //zrz
    {
      path: '/mainpage',
      name: 'MainPage',
      component: MainPage,
      children: [
        {
          path: '/note',
          component: Note
        },
        {
          path: '/zrzorder',
          component: ZrzOrder
        },
        {
          path: '/seting',
          component: Seting
        },  
        {
          path: '/ordertocomment1',
          component: OrdertoComment1
        },   
      ],
    },
  ]
})
