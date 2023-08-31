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
            <span>{{ props.row.name }}</span>
          </el-form-item>
          <el-form-item label="所属店铺">
            <span>{{ props.row.shopname }}</span>
          </el-form-item>
          <el-form-item label="商品分类">
            <span>{{ props.row.classification }}</span>
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
      prop="name">
    </el-table-column>
    <el-table-column
      label="店铺地址"
      prop="address">
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
            // 通过外部 API 获取表格数据，并更新到 tableData 数组中
            // 替换以下示例代码为您的实际 API 调用
            // 例如：使用 Axios 发送请求获取数据
            axios
                .get("/api/onlineshop/browse").then(response => {
                console.log(11111)
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