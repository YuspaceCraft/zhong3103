<template>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="username">
        <el-input style="width: 500px;" clearable v-model="ruleForm.username" placeholder="请输入用户名(不超过10个字符)" maxlength="10" show-word-limit></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input show-password style="width: 500px;" clearable type="password" v-model="ruleForm.pass" autocomplete="off" placeholder="请输入密码(不超过15个字符)" maxlength="15" show-word-limit></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input show-password style="width: 500px;" clearable type="password" v-model="ruleForm.checkPass" autocomplete="off" placeholder="请再次确认密码" maxlength="15"></el-input>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input style="width: 500px;" clearable v-model.number="ruleForm.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input style="width: 500px;" clearable v-model="ruleForm.phone" placeholder="如:10086"></el-input>
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input style="width: 500px;" clearable v-model="ruleForm.address" placeholder="如:山东省烟台市芝罘区xx小区3号楼"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script>
   import axios from 'axios'
  import {reqfinduser,requpdateuser,reqId} from '../utils/request.js'
    export default {
      data() {
        var checkAge = (rule, value, callback) => {
          if (!value) {
            return callback(new Error('年龄不能为空'));
          }
          setTimeout(() => {
            if (!Number.isInteger(value)) {
              callback(new Error('请输入数字值'));
            } else {
              if (value < 18) {
                callback(new Error('必须年满18岁'));
              } else {
                callback();
              }
            }
          }, 1000);
        };
        var validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入密码'));
          } else {
            if (this.ruleForm.checkPass !== '') {
              this.$refs.ruleForm.validateField('checkPass');
            }
            callback();
          }
        };
        var validatePass2 = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请再次输入密码'));
          } else if (value !== this.ruleForm.pass) {
            callback(new Error('两次输入密码不一致!'));
          } else {
            callback();
          }
        };
        return {
          userid: 1,
          ruleForm: {
            username: '',
            phone: '',
            address: '',
            pass: '',
            checkPass: '',
            age: ''
          },
          rules: {
            pass: [
              { validator: validatePass, trigger: 'blur' }
            ],
            checkPass: [
              { validator: validatePass2, trigger: 'blur' }
            ],
            age: [
              { validator: checkAge, trigger: 'blur' }
            ]
          }
        };
      },
      mounted() {
        this.fetchTableData();
        // reqData().then((res)=>{
        //   console.log(res);
        //   this.tableDatast = res.data;
        // })
      },
      methods: {
        fetchTableData() {
          
          reqId().then((res) => {
               console.log("当前的用户Id:"+res.data)
               this.userid = res.data;
               
                   reqfinduser(this.userid).then((response) => {
                   console.log(response);
                   this.ruleForm.username = response.data.username;
                // this.ruleForm.pass = response.data.password;
                   this.ruleForm.age = response.data.age;
                   this.ruleForm.address = response.data.address;
                   this.ruleForm.phone = response.data.phone;
               });

          });

        },

        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              alert('更改成功!');
            } else {
              console.log('更改失败!!!');
              return false;
            }
          });

          requpdateuser(this.userid,this.ruleForm.username,this.ruleForm.pass,this.ruleForm.age,
              this.ruleForm.phone,this.ruleForm.address).then(res => {
              console.log("update success");
          });


        },

        resetForm(formName) {
          this.$refs[formName].resetFields();
        }
      }
    }
  </script>
<style> 
el-input{
  width: 1000px;
}
</style>