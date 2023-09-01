<template>
    <el-row class="tac">
      <el-col :span="5">
        <h2>SHOPPING</h2>
        <el-menu
          router
          default-active="1"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
        >
          <el-menu-item index="lslgoodsitem">
            <router-link to="lslgoodsitem">  
            <i class="el-icon-location"></i>
          </router-link>
          <span slot="title">商品管理</span>
          </el-menu-item>
          <el-menu-item index="lslorder">
            <router-link to="lslorder">
            <i class="el-icon-menu"></i>
           </router-link>
            <span slot="title">订单管理</span>
          </el-menu-item>
          <el-menu-item index="lsluser">
            <router-link to="lsluser">
               <i class="el-icon-document"></i>
            </router-link>
           
            <span slot="title">用户管理</span>
          </el-menu-item>
          <el-menu-item index="lslcomment">
            <router-link to="lslcomment"> 
               <i class="el-icon-setting"></i>
            </router-link>
           
            <span slot="title">评论管理</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col class="Right" :span="19">
  
        <router-view />
  
      </el-col>
    </el-row>
   
  </template>
  <script>
  
  export default {
    date() {
      return {};
    },
  
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  };
  </script>
  
  <style scoped>
  .Right {
    background-color: rgb(255, 255, 255);
   
  }
  </style>