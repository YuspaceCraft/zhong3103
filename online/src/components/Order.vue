<template>
    <div class="browser">
      <div class="head">
        <el-input
          placeholder="请输入内容"
          v-model="input1" clearable>
          <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input>
        <el-button type="primary" icon="el-icon-search" @click="seachorder()">搜索</el-button>
  
        <el-row class="add">
          <el-button type="primary" @click="deleteSelected()">删除</el-button>
          
        
        </el-row>  <el-button @click="toggleSelection()">取消选择</el-button>
      </div>
  
  <el-table
      :data="ordertableData"
      :default-sort="{ prop: 'id', order: 'ascending' }"
      border
      :max-height="600"
      fit
      ref="multipleTable"
      @selection-change="handleSelectionChange" 
      highlight-current-row
      style="width: 100%">

      <el-table-column
      fixed
      
      type="selection"
      width="55">
    </el-table-column>
      
      <el-table-column
        prop="id"
        label="商品号"
        v-if="false"
        width="120">
      </el-table-column>
      <el-table-column
        prop="img"
        label="商品图片"
        width="150">
        <template slot-scope="scope">
            <el-image :src="scope.row.img" alt="商品图片" style="width: 100px; height: 100px; "/>
          </template>
      </el-table-column>
      <el-table-column
        prop="label"
        label="商品名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="price"
        label="价格"
        width="120">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="数量"
        width="150">
      </el-table-column>
      <el-table-column
        prop="user"
        label="购买用户"
        width="170">
      </el-table-column>
      <el-table-column
        prop="total"
        label="总价"
        width="160">
        <template slot-scope="scope2">{{ scope2.row.price*scope2.row.amount }}</template>
      </el-table-column>
      <el-table-column
        prop="issend"
        label="是否发货"
        v-if="false"
        width="210">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="large">状态查看</el-button>
          
   
        </template>
      </el-table-column>
    </el-table>


  <!-- 订单状态和确认发货 -->
<el-dialog title="订单状态" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item  v-if="false" label="商品号">
          <el-input
          :readonly="true"
            v-model="form.id"
           
            style="width: fit-content; "
          ></el-input>
        </el-form-item>
    <el-form-item label="购买用户">
          <el-input
          :readonly="true"
            v-model="form.user"
            style="width: fit-content; "
          ></el-input>
        </el-form-item>
        <el-form-item label="商品名称">
          <el-input
          :readonly="true"
            v-model="form.name"
            style="width: fit-content; "
          ></el-input>
        </el-form-item>
        <el-form-item label="商品单价">
          <el-input
          :readonly="true"
            v-model="form.price"
            style="width: fit-content;  "
          ></el-input>
        </el-form-item>
        <el-form-item label="购买数量">
          <el-input
          :readonly="true"
            v-model="form.num"
            style="width: fit-content; "
          ></el-input>
        </el-form-item>

       <el-form-item label="总额">
          <el-input
          :readonly="true"
            v-model="form.total"
            style="width: fit-content; "
          ></el-input>
        </el-form-item>

        <el-form-item label="是否发货">
          <el-input
          :readonly="true"
            v-model="form.issend"
            style="width: fit-content; "
          ></el-input>
        </el-form-item>



     
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">返 回</el-button>
    <el-button v-if="issendB" type="primary" @click="truesend()">确 认 发 货</el-button>
  </div>
</el-dialog>

 
    </div>
  </template>
  
  <script>
  import { reqOrder } from '../utils/request';
  import { reqfindorder } from '../utils/request';
  import { reqDelorder } from '../utils/request';
  import { requpdatesend } from '../utils/request';
  
  export default {
    data(){
      return{
        deletearr:[],
        input1: '',
        issendB: true,
        rows:'',
  
        multipleSelection: [],
        dialogFormVisible: false,
        ordertableData: [],
        
          form: {
            id:"",
            user:"",
            name: "",
            price: "",
            num: "",
            total:"",  
            issend:""        
            
      },
      formLabelWidth: "120px"
      
      }
    },
    mounted(){
      reqOrder().then(res => {
        console.log(res);
        this.ordertableData = res.data;
       
      })
    },
    
    methods: {
      //模糊查询订单数据
      seachorder(){
         var label = this.input1;
        reqfindorder(label).then( res => {
          console.log(res);
          this.ordertableData = res.data;
        })
      },


      //获取订单详情
      handleClick(row) {
        this.dialogFormVisible = true,
      this.form = {
        id: row.id,
        user:row.user,
        name: row.label,
        price: row.price,
        num: row.amount,
        total: row.price * row.amount,
        issend: row.issend
          
        },
        console.log(row);
          if(this.form.issend == "是"){
            this.issendB = false;
          }else{
            this.issendB = true;
          }
      },

      //分页相关
        handleCurrentChange(val) {
        this.currentPage = val;
      },

      //多选,切换表格中多行的选中状态
      toggleSelection(rows) {
        if (rows) {

          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
            
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      //获取选中数据的id
      handleSelectionChange(val) {
        this.deletearr = [];
        this.multipleSelection = val;
        for(let i=0; i<val.length; i++){
          this.deletearr.push(val[i].id);
        }
        
      },

       //批量删除
    deleteSelected(){
      for(let i=0; i<this.deletearr.length; i++){
          reqDelorder(this.deletearr[i]).then((res)=>{
              console.log("delete-success");
              this.ordertableData = res.data;
          })
        }
        if(this.deletearr.length){
          this.$message({
          type: 'success',
          message: '所选项已删除成功!'
        });
        }else{
          this.$message({
          type: 'warning',
          message: '未选择任何选项!'
        });
        }
        
       
    },
    
      //确认发货
      truesend(){
        
        var id = this.form.id;
       requpdatesend(id).then(res =>{
        console.log(res);
        this.ordertableData = res.data;
       });
       this.dialogFormVisible = false;
      }


    }
   

    // handleDelete(row){
    //  // console.log("row:"+row);
    //  const loading = this.$loading({
    //       lock: true,
    //       text: 'Loading',
    //       spinner: 'el-icon-loading',
    //       background: 'rgba(0, 0, 0, 0.7)'
    //     });
    //   reqdeletecart(row).then((res)=>{
    //         //console.log(res);
    //   })
    //   setTimeout(() => {
    //     loading.close();
    //     this.$message({
    //       type: 'success',
    //       message: '移除成功!'
    //   });
    //     reqgetorders().then((newdata)=>{
    //       this.arr = newdata.data;
    //     })
    //   }, 2000);
      
    // },




  }
  </script>
  
  <style scoped>
  
  .browser{
    display: flex;
    flex-direction: column;
  }
  .head{
    width: 500px;
    margin-top: 15px;
    display: flex;
  }
  
  </style>