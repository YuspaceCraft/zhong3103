<template>
    <div>
      <div class="search-container">
        <el-input v-model="searchText" placeholder="搜索" class="custom-input" clearable></el-input>
        <el-button @click="performSearch" type="primary">搜索</el-button>
      </div>
      <el-table
        stripe
        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
        style="width: 100%"
        max-height="400">
        <el-table-column
          fixed
          prop="date"
          label="日期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="orderid"
          label="已收货的订单编号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="商品名称"
          width="120">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址"
          width="300">
        </el-table-column>
        <el-table-column
          prop="num"
          label="数量"
          width="120">
        </el-table-column>
        <el-table-column
          prop="price"
          label="价格"
          width="120">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="240">

          <template slot-scope="scope">
            <el-button type="text" @click="click11(scope.$index,scope.row.orderid)">添加/查看评价</el-button>

             <el-dialog title="我的评价" 
                append-to-body
                :visible.sync="dialogTableVisible">
                <el-form :model="form">
                 <el-input
                   clearable
                   type="textarea"
                   :autosize="{ minRows: 2, maxRows: 4}"
                   placeholder="请输入内容"
                   v-model="form.comment">
                  </el-input>
                 </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="click13()">取 消</el-button>
                  <el-button type="primary" @click="click12()">确 定</el-button>
                </div>
             </el-dialog>
          </template>
        </el-table-column>
      </el-table>
  
      <!-- 分页实现 -->
      <div class="pagehelp">
                <el-pagination
                background
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-size="pageSize"
                :total="tableData.length"
                layout="total,prev,pager,next">
                </el-pagination>
      </div>
    </div>
  </template>
    
  <script>
  import axios from 'axios'
  import {reqSearch,reqData,requpdate} from '../utils/request.js'
    export default {
      data() {
        return {
            form: {
                comment: '',
            },
          tableData: [],
          searchText :'', 
          textarea: '',
          currentPage: 1,
          pageSize: 6,
          totalItems: 0,
          dialogTableVisible: false,
          lnum: 0,
          currentlist: 0,
        }
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
              // 通过外部 API 获取表格数据，并更新到 tableData 数组中
              // 替换以下示例代码为您的实际 API 调用
              // 例如：使用 Axios 发送请求获取数据
              axios
                  .get("/api/order/findall").then(response => {
                  this.tableData = response.data;
                  this.totalItems = response.data.length;
              })
                  .catch(error => {
                  console.error(error);
              });
        },
        performSearch(){
          // 获取到用户输入的这个搜索关键字
          var search = this.searchText;
              reqSearch({ orders: search }).then(res => {
                  console.log(res);
                  this.tableData = res.data;
                  this.currentPage = 1;
              });
        },
        handleCurrentChange(currentPage) {
              this.currentPage = currentPage;
          },
        handleClick(row) {
          console.log(row);
        },

        click11(index,list1){
        this.dialogTableVisible = true;
        this.lnum=list1;
        this.currentlist=index;
        console.log(this.lnum);
        console.log(index);

        var search = this.searchText;
        reqSearch({ orders: search }).then(res => {
            console.log(res);
            console.log("当前页面是 "+this.currentPage,"下标是 "+((this.currentPage - 1) * this.pageSize + index)),
            this.form.comment = res.data[((this.currentPage - 1) * this.pageSize + index)].comment;
            var aa = this.form.comment
            console.log(aa);
        });

      },
        click12(){
            this.dialogTableVisible = false;
            console.log(this.form.comment),
            console.log(this.lnum),
            requpdate(this.lnum,this.form.comment).then(res => {
                console.log('更新评论成功')
            }); 
            this.$message({
            showClose: true,
            message: '更新评价成功',
            type: 'success'
            });
        },
        
        click13(){
            this.dialogTableVisible = false;
            this.$message({
            showClose: true,
            message: '您已取消更新评论'
            });
        },
      },
    }
  </script>
  
  <style>
  .search-container {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }
    
  .search-container > * {
    margin-right: 10px;
  }
  
  .custom-input {
    width: 480px; /* 设置您想要的宽度值 */
  }
  </style>