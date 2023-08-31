<template>
  <!-- <router-link to="/home">注册</router-link> -->
  <el-container>
    <el-header>Welcome to CosmShop</el-header>
    <el-main>
      <div style="margin: 20px;"></div>
      <div class="form">
        <el-form :label-position="labelPosition" label-width="80px" :model="form">
          <el-form-item label="用户名">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认密码">
            <el-input v-model="form.password1" @input="passwordtest" show-password></el-input>
            <p v-if="passwordError" class="error-message">{{passwordError}}</p>
          </el-form-item>
          <el-form-item label="地区">
            <el-input v-model="form.location" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.phone" autocomplete="off" @input="phonetest"></el-input>
            <p v-if="phoneError" class="error-message">{{phoneError}}</p>
          </el-form-item>
          <el-form-item label="email">
            <el-input v-model="form.email" autocomplete="off" @input="emailtest"></el-input>
            <p v-if="emailError" class="error-message">{{ emailError }}</p>
          </el-form-item> 
          <el-form-item label="地址">
            <el-input v-model="form.address" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="returnlogin">返回登录</el-button>
            <el-button type="primary" @click="register">确认注册</el-button>
          </el-form-item>
        </el-form>
      </div>   
    </el-main>
  </el-container>
</template>
<script>
import { register } from 'echarts/lib/CoordinateSystem';
import { reqadduser } from '../utils/request.js'
export default {
    methods: {
      phonetest(){
        const phonePattern = /^1\d{10}$/;
        if (!phonePattern.test(this.form.phone)) {
          this.phoneError = '无效电话号码';
        } else {
          this.phoneError = '';
        }
      },
      emailtest(){
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (!emailPattern.test(this.form.email)) {
          this.emailError = '无效邮箱';
        } else {
          this.emailError = '';
        }
      },
      passwordtest(){
        if(this.form.password!=this.form.password1){
          this.passwordError = '密码不一致,请检查';
        }else{
          this.passwordError = '';
        }
      },
      returnlogin(){
        this.$router.push('/')
      },
      register(){
        reqadduser(this.form.username,this.form.password,this.form.location
        ,this.form.phone,this.form.account,this.form.email,this.form.address).then((res)=>{
                console.log("add-success");
        })
        const loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });
        setTimeout(() => {
          loading.close();
          this.$message({
            type: 'success',
            message: '注册成功!'
          });
          this.$router.push('/');
        }, 2000);
      }
    },
    data() {
      return {
        labelPosition: 'left',
        form: {
          username: '',
          password: '',
          password1: '',
          location: '',
          phone: '',
          account: 50,
          email: '',
          address: '',
        },
        phoneError: null,
        emailError: '',
        passwordError: '',
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

.error-message {
  font-size: 5px;
  color: red;
}
</style>
