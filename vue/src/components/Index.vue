<template>
  <!-- <router-link to="/home">注册</router-link> -->
  <el-container>
    <el-header>Welcome to CosmShop</el-header>
    <el-main>
      <div style="margin: 20px;"></div>
      <div class="form">
        <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
          <el-form-item label="用户名">
            <el-input v-model="formLabelAlign.name"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="formLabelAlign.password" show-password></el-input>
          </el-form-item>
          <el-form-item label="身份">
            <el-select v-model="value" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="goregister">注册</el-button>
            <el-button type="primary" @click="onSubmit">登录</el-button>
          </el-form-item>
        </el-form>
      </div>   
    </el-main>
  </el-container>
</template>
<script>
import { reqgetuser } from '../utils/request.js'
export default {
    mounted(){
      reqgetuser().then((res)=>{
        //console.log(res.data);
        this.arr = res.data;
      })
    },
    methods: {
      goregister(){
        this.$router.push('/register');
      },
      onSubmit(){
        const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        let flag = false;
        for(let i=0;i<this.arr.length;i++){
          if((this.formLabelAlign.name==this.arr[i].username)
          &&(this.formLabelAlign.password==this.arr[i].password)){  
            flag = true; break;
          }
        }
        setTimeout(() => {
              loading.close();
              if(flag){
                this.$router.push({name: 'Home',params: {params: this.arr}});
                this.$message({
                  message: '登录成功',
                  type: 'success'
                });
              }else{
                this.$message({
                  message: '用户名或密码错误',
                  type: 'error'
                });
              }
            }, 2000);
        
      }
    },
    data() {
      return {
        arr: [],
        labelPosition: 'left',
        formLabelAlign: {
          name: '',
          password: ''
        },
        options: [{
          value: '选项1',
          label: '普通用户'
        }, {
          value: '选项2',
          label: '后台用户'
        }, {
          value: '选项3',
          label: '管理员'
        }],
        value: ''
      };
    }
  }
</script>


<style scoped>
.form{
  display: flex;
  align-items: center;
  justify-content: center;
}
.el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}


.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 200px;
}
</style>
