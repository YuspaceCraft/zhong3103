<template>
    <div class="browser">
      <div class="head">
      
        <el-input
          style="width:  fit-content;"
          placeholder="请输入用户名信息"
          v-model="searchname" clearable>
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>

        <el-input
        style="width: fit-content;"
          placeholder="请输入电话信息"
          onkeyup="value=value.replace( /\D+/, '')"
         
          v-model="searchphone" clearable>
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>

        <el-input
        style="width: fit-content;"
          placeholder="请输入邮箱信息"
          onkeyup="value=value.replace(/[^\w@.]+/, '')"
          v-model="searchemail" clearable>
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>


        <el-input
        style="width: fit-content;"
          placeholder="请输入地区信息"
          v-model="searchlocation" clearable>
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>

        <el-input
        style="width: fit-content;"
          placeholder="请输入地址信息"
          v-model="searchaddress" clearable>
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
        
        <el-button type="primary" icon="el-icon-search"  @click="searchuser()">搜索</el-button>
  
        <el-row class="add">
           <el-button type="primary"  @click="useradd()">用户添加</el-button>
        </el-row>


                          <!-- 添加用户 -->
        <el-dialog title="填写用户信息" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules"  ref="ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input
            clearable
            placeholder="请输入用户名"
            v-model="form.username"
            style="width: fit-content;"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            clearable
            placeholder="请输入密码"
            show-password
            v-model="form.password"
            style="width: fit-content; "
          ></el-input>
        </el-form-item>


        <el-form-item label="电话号码" prop="phone">
          <el-input
          clearable
          placeholder="请输入电话号码"
            type="tel"
            onkeyup="value=value.replace( /\D+/, '')"
            v-model.trim="form.phone"
            style="width: fit-content;  "
          ></el-input>
        </el-form-item>
        <el-form-item label="电子邮件" prop="email">
          <el-input
            clearable
            placeholder="请输入电子邮件"
            v-model="form.email"
            onkeyup="value=value.replace( ^[\w\.-]+@[\w\.-]+\.\w+$ , '')"
            type="email"
            style="width: fit-content; "
          ></el-input>
        </el-form-item>

        <el-form-item label="所在地区" prop="location">
          <el-input
            clearable
            placeholder="请输入地区"
            v-model="form.location"
      
            style="width: fit-content; "
          ></el-input>
        </el-form-item>

        <el-form-item label="居住地址" prop="address">
          <el-input
            clearable
            placeholder="请输入详细地址"
            v-model="form.address"
            style="width: fit-content; "
          ></el-input>
        </el-form-item>

        
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="trueadduser('ruleForm')"
          >确 定</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </div>
    </el-dialog>


      </div>
  
  <el-table
      :data="usertableData.slice(
                    (currentPage - 1) * pageSize,
                    currentPage * pageSize
                  )"
                  :default-sort="{ prop: 'id', order: 'ascending' }"
      border
      fit
      highlight-current-row
      style="width: 100%">
      <el-table-column
        prop="id"
        label="用户序号"
        v-if="false"
        width="120">
      </el-table-column>
      <el-table-column
        fixed
        prop="username"
        label="用户名"
        width="120">
        
      </el-table-column>
      <el-table-column
        prop="password"
        label="用户密码"
        width="120">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="电话号码"
        width="120">
      </el-table-column>
      <el-table-column
        prop="email"
        label="电子邮箱"
        width="170">
      </el-table-column>
      <el-table-column
        prop="account"
        label="余额"
        width="100">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        width="300">
      </el-table-column>
      <el-table-column
        prop="location"
        label="地区"
        width="100">
      </el-table-column>
      <el-table-column
        prop="zip"
        label="备注"
        width="200">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="large">删除</el-button>
        </template>
      </el-table-column>
    </el-table>








    <el-pagination
                background
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-size="pageSize"
                layout="total,prev,pager,next"
                :total="usertableData.length"
              >
              </el-pagination>






  
    </div>
  </template>
  
  <script>
   
   import { requser } from '../utils/request';
   import { reqfinduser } from '../utils/request';
   import { reqAdduser } from '../utils/request';
   import { reqDeluser } from '../utils/request';

  export default {
    data(){
      return{
        usertableData:[],
        searchaddress: '',
        searchemail:"",
        searchlocation:'',
        searchname:'',
        searchphone:'',
        
        dialogFormVisible: false,
        currentPage: 1, // 当前页码
        pageSize: 7, // 每页显示的行数
       
          
      form: {
        username: "",
        password: "",
        phone: "",
        email: "",
        location: "",
        address:""
       
      },
      formLabelWidth: "120px"  ,
      

      
      
      rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'blur' }
          ],
          password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
          ],
          phone: [
              { required: true, message: '请输入联系方式',trigger: 'blur'},
              {  min: 11, max: 11, message: '请输入正确的号码'}
          ],
          email: [
          
          { required: true, message: '请输入邮箱地址' },
               { pattern: /^[\w\.-]+@[\w\.-]+\.\w+$/, message: '请输入正确的邮箱地址' }
          ],
          location: [
          { required: true, message: '请输入地区', trigger: 'blur' },
            { min: 3, max: 16, message: '长度在 3 到 16 个字符'}
          ],
          address: [
          { required: true, message: '请输入详细地址', trigger: 'blur' },
            { min: 3, max: 16, message: '长度在 3 到 30 个字符'}
          ]
         
        }


      }
    },

    mounted(){
      requser().then(res => {
        console.log(res);
        this.usertableData = res.data;
      })
    },

    methods: {
      //删除用户信息
      handleClick(row) {
          console.log(row);
          var id = row.id;
          reqDeluser(id).then( res => {
            console.log(res);
            this.usertableData = res.data;
          }),
          this.$message({
          type: 'success',
          message: '所选项已删除成功!'
          })
        },

        // 分页相关
        handleCurrentChange(val) {
        this.currentPage = val;
      },

      //模糊搜索用户
      searchuser(){
        var username = this.searchname;
        var phone = this.searchphone;
        var email = this.searchemail;
        var location = this.searchlocation;
        var address = this.searchaddress;
        reqfinduser(username,phone,email,location,address).then( res => {
          console.log(res);
          this.usertableData = res.data;
        })

      },
      //添加用户
      useradd(){
        this.dialogFormVisible = true;
      },
      trueadduser(formName){


        this.$refs[formName].validate((valid) => {
          if (valid) {


            var username = this.form.username;
        var password = this.form.password;
        var phone = this.form.phone;
        var email = this.form.email;
        var location = this.form.location;
        var address = this.form.address;
        var account = "0.00"
          reqAdduser(username,password,phone,account,email,location,address).then( res => {
            console.log(res);
            this.usertableData = res.data;
          }),
         
          this.dialogFormVisible = false;
          

           
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    

       
      },



    }
  
  

  </script>
  
  <style scoped>
  
  .browser{
    display: flex;
    flex-direction: column;
  }
  .head{
    width: 1000px;
    margin-top: 30px;
    display: flex;
  }
  
  </style>