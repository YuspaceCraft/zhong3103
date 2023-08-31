<template>
  <!-- <router-link to="/home">注册</router-link> -->
  <div>

    <el-container>
    <el-header>Header</el-header>
    <el-main>
      <div style="margin: 20px;"></div>
      <div class="form">
        <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
          <el-form-item label="用户名">
            <el-input v-model="formLabelAlign.username"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="formLabelAlign.password" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">登录</el-button>
            <el-button type="primary"><router-link to="/register">注册</router-link></el-button>
          </el-form-item>
        </el-form>
      </div>   
    </el-main>
  </el-container>
  </div>
</template>

<script>
import { reqlogin } from '../utils/request.js';

export default {
  data() {
        return {
            cuser: 'zzz',
            loginid: 0,
            labelPosition: 'left',
            formLabelAlign: {
                username: '',
                password: '',
            }
        };
    },

    methods: {
        onSubmit() {
            console.log(this.formLabelAlign.username);
            console.log(this.formLabelAlign.password);
            this.cuser = this.formLabelAlign.username;

            reqlogin(this.formLabelAlign.username,this.formLabelAlign.password).then((res) => {
              var res1 = res.data;
              if(res1 != 0){
                this.loginid = res1;

                this.$router.push({name: 'MainPage',params: {params: this.loginid}});
              } else
                alert("用户名或密码错误!");
            });

            // if (this.formLabelAlign.username == "zhang" && this.formLabelAlign.password == "123456")
            //     this.$router.push('/mainpage');
            // else
            //     alert("密码错误!");

        }
    },

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
