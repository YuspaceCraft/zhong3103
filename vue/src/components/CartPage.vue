<template>
  <el-row class="tac">
    <el-col :span="5">
      <h2>SHOPPING   <p>{{username}}<i class="el-icon-switch-button" @click="out"></i></p></h2>
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose">
        <el-menu-item index="1" @click="tohome">
          <i class="el-icon-goods"></i>
          <span slot="title">商品管理</span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-shopping-cart-full"></i>
          <span slot="title">订单管理</span>
        </el-menu-item>
        <el-menu-item index="3" @click="touserpage">
          <i class="el-icon-user"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="4" @click="tocommentpage">
          <i class="el-icon-star-off"></i>
          <span slot="title">评论管理</span>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col class="Right"  :span="19">
      <Cart></Cart>
    </el-col>
  </el-row>
  
</template>
<script>
import Cart from './Cart.vue'
export default {
    components: {
      Cart
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      tohome(){
        this.$router.push({name: 'Home',params: {params: this.arr}});
      },
      touserpage(){
        this.$router.push({name: 'UserPage',params: {params: this.arr}});
      },
      tocommentpage(){
        this.$router.push({name: 'CommentPage',params: {params: this.arr}});
      },
      out(){
        this.$router.push('/');
      }
    },
    data(){
      return{
        arr: [],
        username: '',
      }
    },
    mounted(){
      this.arr = this.receivedParam;
      if(this.arr==null){
        this.$message({
          message: '未登录,非法访问,请返回登录',
          type: 'error'
        });
        this.$router.push('/');
      }
      this.username = this.arr[0].username;
    },
    computed: {
      receivedParam() {
        return this.$route.params.params; // 使用 'params' 作为键来获取参数
      }, 
    },
  }
</script>


<style scoped>
.Right{
  background-color: rgb(177, 255, 140);
}
</style>
