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
        label="订单编号"
        width="120">
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
          <el-button type="text" size="small" @click="opensure(scope.$index,scope.row.orderid)">确认收货</el-button>
          <el-button type="text" size="small" @click="handleDelete(scope.$index,scope.row.orderid)">取消订单</el-button>
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
import {reqSearch,reqDelete} from '../utils/request.js'
  export default {
    data() {
      return {
        tableData: [],
        searchText :'', 
        currentPage: 1,
        pageSize: 6,
        totalItems: 0,
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
      opensure(index,id) {
        this.$confirm('确认是否收货', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.tableData.splice(index, 1);
          reqDelete({ orderid: id }).then(res => {
                console.log("删除成功");
          });

          this.$message({
            type: 'success',
            message: '收货成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消收货'
          });          
        });
      },
      handleDelete(index,id) {
        this.$confirm('此操作将取消该订单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 向后端发送请求，删除数据库中的数据
          this.tableData.splice(index, 1);
            // axios.delete('/api/equipment/delete/{id}').then(res => {
            //   console.log('删除成功')
            // })
            console.log(index);
            console.log(id);
            reqDelete({ orderid: id }).then(res => {
                console.log("删除成功");
            });

          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      }

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