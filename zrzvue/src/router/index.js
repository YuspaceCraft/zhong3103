import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/components/MainPage'
import Order from '@/components/Order'
import Note from '@/components/Note';
import Seting from '@/components/Seting';
import Index from '@/components/Index'
import Register from '@/components/Register'
import OrdertoComment1 from '@/components/OrdertoComment1'

Vue.use(Router)

export default new Router({
  routes: [
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
          path: '/order',
          component: Order
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
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    
  ]
})
