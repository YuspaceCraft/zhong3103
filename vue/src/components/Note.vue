<template>
    <div>
      <el-table
      stripe
      :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="商品名称">
              <span>{{ props.row.label }}</span>
            </el-form-item>
            <el-form-item label="价格">
              <span>{{ props.row.price }}</span>
            </el-form-item>
            <el-form-item label="销量">
              <span>{{ props.row.soldnum }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="商品 ID"
        prop="id">
      </el-table-column>
      <el-table-column
        label="商品名称"
        prop="label">
      </el-table-column>
      <el-table-column
        label="商品描述"
        prop="description">
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
  
  <style>
    .demo-table-expand {
      font-size: 0;
    }
    .demo-table-expand label {
      width: 90px;
      color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
      margin-right: 0;
      margin-bottom: 0;
      width: 50%;
    }
  </style>
  
  <script>
  import {reqBrowse} from '../utils/request.js'
  import axios from 'axios'
    export default {
      data() {
        return {
          currentPage: 1,
          pageSize: 10,
          totalItems: 0,
          tableData:[],
          // tableData: [{
          //   id: '12987122',
          //   name: '好滋好味鸡蛋仔',
          //   category: '江浙小吃、小吃零食',
          //   desc: '荷兰优质淡奶，奶香浓而不腻',
          //   address: '上海市普陀区真北路',
          //   shop: '王小虎夫妻店',
          //   shopId: '10333'
          // }, {
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
              axios
                  .get("/api/jifen/browse").then(response => {
                  //console.log(11111)
                  this.tableData = response.data;
                  this.totalItems = response.data.length;
              })
                  .catch(error => {
                  console.error(error);
              });
          },
          handleCurrentChange(currentPage) {
              this.currentPage = currentPage;
          },
      } 
    }
  </script>