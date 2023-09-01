<template>
  <div class="Cart">
    <el-table
    ref="multipleTable"
    :data="arr"
    tooltip-effect="dark"
    style="width: 100%"
    @selection-change="handleSelectionChange" class="table1">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        prop="id"
        label="商品id"
        width="120">
      </el-table-column>
      <el-table-column
        label="图片"
        width="120">
        <template slot-scope="scope"><img :src="scope.row.img" alt=""></template>
      </el-table-column>
      <el-table-column
        prop="label"
        label="商品名"
        width="120">
      </el-table-column>
      <el-table-column
        label="金额"
        show-overflow-tooltip>
        <template slot-scope="scope2">{{ scope2.row.price*scope2.row.amount }}</template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope1">
          <el-input-number v-model="scope1.row.amount" @change="handleChange" :min="1" :max="10" label="描述文字"></el-input-number>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope1.row.id)">移除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="cartbottom" style="margin-top: 20px"> 
      <div>
          <el-button type="primary" @click="toggleSelection()">取消选择</el-button>
          <el-button type="primary" @click="deleteSelected">删除选中项</el-button>
      </div> 
      <div>
        <h4>总价格为:{{total}}</h4>
        <el-button type="primary" @click="calculate">结算</el-button>
      </div>
      
    </div>
    <div></div>
  </div>
</template>

<script>
import { reqgetorders } from '../utils/request.js'
import { requpdatecart } from '../utils/request.js'
import { reqfindBycommId } from '../utils/request.js'
import { reqdeletecart } from '../utils/request.js'
import { reqgetuser } from '../utils/request.js'
import { reqbuything,reqaddorder1 } from '../utils/request.js'
export default {
  mounted(){
      reqgetorders().then((res)=>{
       // console.log(res.data);
        this.arr = res.data;
      }),
      reqgetuser().then((res)=>{
        this.account = res.data[0].account;
        //console.log(this.account);
      })

  },
  data(){
    return{
        multipleSelection: [],
        num: 1,
        arr: [],
        arr1: [],
        total: 0,
        deletearr:[],
        account: 0,
        pageStart: 1,
        pageEnd: 5,
        currentPage: 1,
        pageSize: 4,
        date: '2023-9-2',
        comment: '暂未评论',
        address1: '山东省烟台市鲁东大学北区',
    }
  },
  methods: {
    toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
    handleSelectionChange(val) {
      //console.log(val);
      let prices = 0;
      this.multipleSelection = val;
      this.deletearr = [];
      for(let i=0; i<val.length; i++){
        if(val[i].price!=null)
        {
          this.deletearr.push(val[i].id);
          prices += val[i].price*val[i].amount;
          requpdatecart(val[i].id,val[i].amount).then((res)=>{
            //console.log("success");
          })
        }
      }
      this.total = prices;
    },
    handleChange(row) {
      //console.log(row);
      this.$refs.multipleTable.toggleRowSelection(row);
      // console.log(val);
    },
    calculate() {
      this.$confirm('点击后支付所选商品,总价格为'+this.total+'元, 是否支付?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        if(this.total==0){
          const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
          });
          setTimeout(() => {
            loading.close();
            this.$message({
              type: 'warning',
              message: '没有选中商品!!'
            });
          }, 2000);
        }
        else if(this.total<=this.account){
          const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
          });
          for(let i=0; i<this.deletearr.length; i++){
            reqfindBycommId(this.deletearr[i]).then((res)=>{
              console.log(res.data);
              reqaddorder1(res.data[0].id+999,this.date,res.data[0].label,this.address1,1,res.data[0].price,this.comment).then((res)=>{
              console.log("addorder-success!!!");
              })
            })
            
            reqdeletecart(this.deletearr[i]).then((res)=>{
              console.log("purchase-success");
            })
          }
          setTimeout(() => {
            loading.close();
            reqbuything(this.account-this.total).then((res)=>{
              console.log(res.data);
            })
            this.$message({
              type: 'success',
              message: '支付成功!'
            });
            reqgetorders().then((res)=>{
            // console.log(res.data);
              this.arr = res.data;
            }),
            reqgetuser().then((res)=>{
              this.account = res.data[0].account;
            //console.log(this.account);
            })
          }, 2000);
        }
        else{
          const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
          });
          setTimeout(() => {
            loading.close();
            this.$message({
              type: 'error',
              message: '余额不足,请充值!!!!'
            });
          }, 2000);
        }       
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消支付'
        });          
      });
    },
    handleDelete(row){
     // console.log("row:"+row);
     const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
      reqdeletecart(row).then((res)=>{
            //console.log(res);
      })
      setTimeout(() => {
        loading.close();
        this.$message({
          type: 'success',
          message: '移除成功!'
      });
        reqgetorders().then((newdata)=>{
          this.arr = newdata.data;
        })
      }, 2000);
      
    },
    deleteSelected(){
      const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
      for(let i=0; i<this.deletearr.length; i++){
          reqdeletecart(this.deletearr[i]).then((res)=>{
              console.log("delete-success");
          })
        }

      setTimeout(() => {
        loading.close();
        this.$message({
          type: 'success',
          message: '所选项已删除成功!'
        });
        reqgetorders().then((newdata)=>{
          this.arr = newdata.data;
        })
      }, 2000);
    }
  },
  
}
</script>

<style scoped>

.cartItem{
  height: 100px;
  background-color: beige;
}
.cartbottom{
  display: flex;
  justify-content: space-between;
}
.cartbottom div{
  display: flex;
  justify-content: space-between;
}
.table1 img{
  width: 100px;
  height: 100px;
}
</style>