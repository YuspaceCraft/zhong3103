<template>
  <div class="User">
    <el-descriptions class="margin-top" title="用户资料" :column="2"  border>
      <template slot="extra">
        <el-button type="primary" size="small" @click="dialogFormVisible = true">操作</el-button>
        <el-dialog title="修改用户资料" :visible.sync="dialogFormVisible">
          <el-form :model="form">
            <el-form-item label="用户名" :label-width="formLabelWidth">
              <el-input v-model="form.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" :label-width="formLabelWidth">
              <el-input v-model="form.password" autocomplete="off" show-password></el-input>
            </el-form-item>
            <el-form-item label="地区" :label-width="formLabelWidth">
              <el-input v-model="form.location" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-input v-model="form.phone" autocomplete="off" @input="phonetest"></el-input>
              <p v-if="phoneError" class="error-message">{{phoneError}}</p>
            </el-form-item>
            <el-form-item label="余额" :label-width="formLabelWidth">
              <el-input v-model="form.account" autocomplete="off" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="email" :label-width="formLabelWidth">
              <el-input v-model="form.email" autocomplete="off" @input="emailtest"></el-input>
              <p v-if="emailError" class="error-message">{{ emailError }}</p>
            </el-form-item> 
            <el-form-item label="地址" :label-width="formLabelWidth">
              <el-input v-model="form.address" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="close1">取 消</el-button>
            <el-button type="primary" @click="confirm1">确 定</el-button>
          </div>
        </el-dialog>
      </template>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          用户名
        </template>
        {{this.arr[0].username}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone"></i>
          手机号
        </template>
        {{this.arr[0].phone}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-location-outline"></i>
          居住地
        </template>
        {{this.arr[0].location}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          备注
        </template>
        <el-tag size="small">学校</el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-coin"></i>
          我的余额
        </template>
        {{this.arr[0].account}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-office-building"></i>
          联系地址
        </template>
        {{this.arr[0].address}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-view"></i>
          密码
        </template>
        {{this.arr[0].password}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-message"></i>
          email
        </template>
        {{this.arr[0].email}}
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
import { reqgetuser } from '../utils/request.js'
import { requpdateuser } from '../utils/request.js'
export default {
  mounted(){
      reqgetuser().then((res)=>{
        //console.log(res.data);
        this.arr = res.data;
        this.form.username = res.data[0].username;
        this.form.password = res.data[0].password;
        this.form.location = res.data[0].location;
        this.form.phone = res.data[0].phone;
        this.form.account = res.data[0].account;
        this.form.email = res.data[0].email;
        this.form.address = res.data[0].address;
      })
      
  },
  data(){
    return{
      arr:[{
        username: '',
        password: '',
        location: '',
        phone: '',
        account: 888,
        email: '',
        address: '',
      }],
      dialogTableVisible: false,
      dialogFormVisible: false,
      form: {
        username: '',
        password: '',
        location: '',
        phone: '',
        account: 888,
        email: '',
        address: '',
      },
      formLabelWidth: '120px',
      phoneError: null,
      emailError: '',
    }
  },
  methods: {
    close1(){
      const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
      setTimeout(() => {
        loading.close();
        this.$message('取消修改');
        reqgetuser().then((res)=>{
          this.arr = res.data;
        this.form.username = res.data[0].username;
        this.form.password = res.data[0].password;
        this.form.location = res.data[0].location;
        this.form.phone = res.data[0].phone;
        this.form.account = res.data[0].account;
        this.form.email = res.data[0].email;
        this.form.address = res.data[0].address;
        })
      }, 500);
      this.dialogFormVisible = false
      this.phoneError = null;
      this.emailError = '';
    },
    confirm1(){
      requpdateuser(this.form.username,this.form.password,this.form.location
      ,this.form.phone,this.form.account,this.form.email,this.form.address).then((res)=>{
              console.log("update-success");
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
          message: '修改成功!'
        });
        reqgetuser().then((newdata)=>{
          this.arr = newdata.data;
        })
      }, 2000);
      this.dialogFormVisible = false
    },
    phonetest(){
      const phonePattern = /^1\d{10}$/;
      if (!phonePattern.test(this.form.phone)) {
        this.phoneError = '无效电话号码';
      } else {
        this.phoneError = null;
      }
    },
    emailtest(){
      const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
      if (!emailPattern.test(this.form.email)) {
        this.emailError = '无效邮箱';
      } else {
        this.emailError = '';
      }
    }
  },
  
}
</script>

<style scoped>
.error-message {
  font-size: 5px;
  color: red;
}
</style>