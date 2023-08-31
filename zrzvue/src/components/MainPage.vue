<template>
    <div class="app-container">
        <el-container style="height: 500px; border: 1px solid #eee">
            <el-aside width="250px" style="background-color: rgb(239, 241, 245)">
              <h2>SHOPPING</h2>
                <el-menu :default-openeds="['1','3']" router>
                    <el-submenu index="1">
                        <template slot="title"><i class="el-icon-goods"></i>商品管理</template>
                            <el-menu-item index="note">
                                <router-link to = "note"></router-link>
                                浏览记录
                                </el-menu-item>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title"><i class="el-icon-shopping-cart-full"></i>订单管理</template>
                        <el-menu-item-group>
                            <el-menu-item index="order">
                                <router-link to = "order"> </router-link>
                                订单状态</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title"><i class="el-icon-s-custom"></i>用户管理</template>
                        <el-menu-item-group>
                            <el-menu-item index="Seting">
                                <router-link to = "Seting"> </router-link>
                                用户设置</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="4">
                        <template slot="title"><i class="el-icon-star-off"></i>评论管理</template>
                        <el-menu-item-group>
                            <el-menu-item index="OrdertoComment1">
                                <router-link to = "/OrdertoComment1"> </router-link>
                                我的评论</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                </el-menu>
            </el-aside>

    <!-- <el-row class="tac">
    <el-col :span="3">
      <h2>SHOPPING</h2>
      <el-menu
        default-active="1"
        class="el-menu-vertical-demo">
        <el-menu-item index="1">
          <i class="el-icon-goods"></i>
          <span slot="title"><router-link to="/note">浏览记录</router-link></span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-shopping-cart-full"></i>
          <span slot="title"><router-link to="/order">订单管理</router-link></span>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-s-custom"></i>
          <span slot="title"><router-link to="/Seting">用户管理</router-link></span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-star-off"></i>
          <span slot="title"><router-link to = "/OrdertoComment1">评论管理</router-link></span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row> -->

        <el-container>
            <el-header>
                <el-dropdown router>
                    <i class="el-icon-setting" style="margin-right: 15px"></i>
                    <el-dropdown-menu slot="dropdown">
                        <!-- <router-link to = "comment">
                            <el-dropdown-item >评论</el-dropdown-item>
                        </router-link>
                        <router-link to = "seting">
                            <el-dropdown-item >设置</el-dropdown-item>
                        </router-link> -->

                        <router-link to = "/">
                            <el-dropdown-item>返回登录</el-dropdown-item>
                        </router-link>
                    </el-dropdown-menu>
                </el-dropdown>
                <span>{{ currentname }}</span>
            </el-header>
        
            <el-main>
                <router-view :key="currentView"/>
            </el-main>
        </el-container>
    </el-container>
</div>
</template>


<script>
import Order from '@/components/Order';
import Note from '@/components/Note';
import Seting from '@/components/Seting';
import Index from '@/components/Index'
import Register from '@/components/Register'
import OrdertoComment1 from '@/components/OrdertoComment1'
import axios from 'axios'
export default {
    components:{
        Index,
        Register,
        Order,
        Note,
        Seting,
        OrdertoComment1
    },
    data() {
        return{
            currentView: null,
            cid: 0,
            currentname:'',
        }
    },
    mounted() {
        this.fetchTableData();
        // reqData().then((res)=>{
        //   console.log(res);
        //   this.tableDatast = res.data;
        // })
    },
    methods:{
      fetchTableData() {
            axios
                .get("/api/login/returnname").then(response => {
                this.currentname = response.data;
            })
                .catch(error => {
                console.error(error);
            });
        },
    },
    computed: {
      receivedParam() {
        this.cid=this.$route.params.params;
        console.log("当前页面是:"+this.cid)
        return this.$route.params.params; // 使用 'params' 作为键来获取参数
      }, 
    },
};
</script>

<style>
.app-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.el-container {
  flex: 1; /* 使用剩余空间 */
  border: 1px solid #eee;
}
.el-header {
    background-color: #99bfef;
    color: #333;
    line-height: 60px;
    text-align: right;
    font-size: 12px;
}
  
.el-aside {
    color: #333;
}
</style>