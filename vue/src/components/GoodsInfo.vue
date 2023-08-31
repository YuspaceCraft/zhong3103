<template>
  <div class="GoodsInfo">
    <div class="Mpage">
      <div class="return"><i class="el-icon-arrow-left" @click="return1"></i></div>
      <div class="limg">
        <img :src="receivedParam.img" alt="">
      </div>
      <div class="wright">
        <div>
          <h1>{{receivedParam.label}}</h1>
          <p>{{receivedParam.description}}</p>
          <p>月销量: {{receivedParam.soldnum}}</p>     
          <p>价格:  {{receivedParam.price}}</p>   
        </div>
        <div class="buy">
          <p>当前总价格为:{{num*receivedParam.price}}</p>
          <el-input-number v-model="num" @click="handleChange" :min="1" :max="10" label="描述文字"></el-input-number>
          <div>
          <el-button type="primary" @click="calculate1">立刻付款</el-button><el-button type="primary" @click="addtoCart">加入购物车</el-button>
          </div>
        </div>
      </div>
    </div>  
  </div>
</template>

<script>
import { reqaddtocart } from '../utils/request.js'
import { reqgetuser } from '../utils/request.js'
import { reqbuything } from '../utils/request.js'
export default {
  data(){
    return{
      input1: '',
      num: 1,
      account: 888,
      arr: [],
    }
  },
  methods: {
    handleChange(value) {
        console.log(value);
      },
      calculate() {
      this.$confirm('点击后支付所选商品,总价格为'+this.num*this.receivedParam.price+'元, 是否支付?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'info'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '支付成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消支付'
        });          
      });
    },
    addtoCart(){
      //console.log(this.receivedParam.value)
      reqaddtocart(this.receivedParam.id,this.receivedParam.img,this.receivedParam.label
      ,this.receivedParam.price,this.num).then((res)=>{
            console.log("add-success!!");
      })
      this.$message({
          message: '已加入购物车',
          type: 'success'
      });
    },
    return1(){
      this.$router.go(-1);
    },
    calculate1(){
      const afford = this.account-this.num*this.receivedParam.price;
      if(afford>=0){
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
            message: '购买成功!'
        });
          reqbuything(this.account-this.num*this.receivedParam.price).then((res)=>{
            console.log("purchase-success!!!");
          })
        }, 2000);
        
      }
      else{
        this.$message.error('余额不足,请充值!!!!');
      }
    },
  },
  computed: {
    receivedParam() {
      return this.$route.params.params; // 使用 'params' 作为键来获取参数
    }, 
  },
  mounted(){
    reqgetuser().then((res)=>{ 
        this.account = res.data[0].account;
        //console.log(this.account);
      })
  },
  
}
</script>

<style scoped>
.GoodsInfo{
  background-color: aquamarine;
  display: flex;
  justify-content: center;
}
.Mpage{
  display: flex;
}
.Mpage img{
  width: 400px;
  height: 400px;
}
.limg{

}
.wright{
  margin-left: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.buy{
  display: flex;
  flex-direction: column;
  align-items:flex-start;
}
.buy div{
  margin: 10px;
}
.return{
  width: 80px;
  font-size: 50px;
}
</style>