<template>
  <!-- <router-link to="/home">注册</router-link> -->
  <el-container>
    <el-header>Header</el-header>
    <el-main>
      <div style="margin: 20px;"></div>
      <div class="form">
        <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
          <el-form-item label="用户名">
            <el-input v-model="formLabelAlign.name"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="formLabelAlign.region" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认密码">
            <el-input v-model="formLabelAlign.region" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit"><router-link to="/">返回登录</router-link></el-button>
            <el-button type="primary"><router-link to="/">确认注册</router-link></el-button>
          </el-form-item>
        </el-form>
      </div>   
    </el-main>
  </el-container>
</template>
<script>
export default {
    methods: {
     
    },
    data() {
      return {
        labelPosition: 'left',
        formLabelAlign: {
          name: '',
          region: '',
          type: ''
        }
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
