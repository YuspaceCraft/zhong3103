<template>
  <div class="browser">
    <div class="head">
      <el-input placeholder="请输入内容" v-model="input1" clearable>
        <i slot="prefix" class="el-input__icon el-icon-search"></i>
      </el-input>
      <el-button type="primary" icon="el-icon-search" @click="searchcommodity()">搜索</el-button>

      <el-row class="add">
        <router-link to="/goodsadd">
          <el-button type="primary">上架商品</el-button></router-link
        >
      </el-row>
    </div>

    <el-table
      :data="
        tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)
      "
      :default-sort="{ prop: 'id', order: 'ascending' }"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column prop="id" label="商品号" v-if="false" width="120">
      </el-table-column>
      <el-table-column fixed prop="img" label="商品图片" width="150">
        <template slot-scope="scope">
          <el-image
            :src="scope.row.img"
            alt="商品图片"
            style="width: 100px; height: 100px; "
          />
        </template>
      </el-table-column>
      <el-table-column prop="label" label="商品名" width="120">
      </el-table-column>
      <el-table-column prop="price" label="价格" width="120">
      </el-table-column>
     
      <el-table-column prop="description" label="描述" width="550"> </el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="large"
            >下架</el-button
          >
          <el-button type="text" size="large" @click="goodsupdate(scope.row)">修改</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 修改商品表单 -->
    <el-dialog title="修改商品信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        
        <el-form-item label="商品号">
          <el-input
            clearable
            :readonly="true"  
            v-model="form.id"
            style="width: fit-content; "
          ></el-input>
        </el-form-item>

        <el-form-item label="图片链接">
          <el-input
            clearable
            v-model="form.img"
            style="width: fit-content;"
          ></el-input>
        </el-form-item>
        <el-form-item label="商品名称">
          <el-input
            clearable
            v-model="form.label"
            style="width: fit-content; "
          ></el-input>
        </el-form-item>
        <el-form-item label="商品单价">
          <el-input
            clearabletype="text"
            onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')"
            v-model="form.price"
            style="width: fit-content;  "
          ></el-input>
        </el-form-item>
       

        <el-form-item label="商品描述">
          <el-input
            autosize
            type="textarea"
            v-model="form.description"
            style="width:400px;"
          ></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="trueupdate()">确 定</el-button
        >
      </div>
    </el-dialog>

    <!-- 分页 -->
    <el-pagination
      background
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="pageSize"
      layout="total,prev,pager,next"
      :total="tableData.length"
    >
    </el-pagination>
  </div>
</template>

<script>
import { reqCommodity } from '../utils/request';
import { reqUpdatecommodity } from '../utils/request';
import { reqfind } from '../utils/request';
import { reqDelcommodity } from '../utils/request';


export default {
  data() {
    return {
      tableData:[],
      input1: "",
      currentPage: 1, // 当前页码
      pageSize: 4, // 每页显示的行数
      

      dialogFormVisible: false,
      form: {
        id:"",
        img: "",
        label: "",
        price: "",
        company: "",
        region: "",
        description: ""
      },
      formLabelWidth: "120px"
    };
  },
  mounted(){
    reqCommodity().then(res => {
      console.log(res);
      this.tableData = res.data;
    })
  },
  methods: {
    handleClick(row) {
      var id = row.id;
      reqDelcommodity(id).then(res =>{
        console.log(res);
        this.tableData = res.data;
      })
      console.log(row);
    },

    //分页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //搜索商品函数
    searchcommodity(){
      var label = this.input1;
      reqfind(label).then(res => {
            console.log(res);
            this.tableData = res.data;
    }).catch(error => {
          console.error('查询失败', error);
        });
        this.currentPage=1;

    },
    //表单获取每行商品数据
    goodsupdate(row) {
      this.dialogFormVisible = true;
      this.form = {
        id:row.id,
        img: row.img,
        label: row.label,
        price: row.price,
        company: row.city,
        region: row.address,
        description: row.description
      };
      
      
    },
//修改商品数据
    trueupdate(){
      var label = this.form.label;
      var img = this.form.img;
      var price = this.form.price;
      var description = this.form.description;
      var id = this.form.id;
      reqUpdatecommodity(label,img,price,description,id).then(res => {
      console.log(res);
      this.tableData = res.data;
    }),
    this.dialogFormVisible = false;
    }
  }
};
</script>

<style scoped>
.browser {
  display: flex;
  flex-direction: column;
}
.head {
  width: 500px;
  margin-top: 15px;
  display: flex;
}
</style>
