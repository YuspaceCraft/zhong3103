<template>
  <div>
    <div class="forms">
      <el-form ref="form" :model="form" label-width="80px">
       
        <el-form-item label="图片链接">
          <el-input  clearable v-model="form.img" style="width: fit-content;"></el-input>
        </el-form-item>
        <el-form-item label="商品名称">
          <el-input  clearable v-model="form.label" style="width: fit-content; "></el-input>
        </el-form-item>
        <el-form-item label="商品单价">
          <el-input  clearabletype="text" onkeyup="value=value.replace(/^\D*(\d*(?:\.\d{0,2})?).*$/g, '$1')" v-model="form.price" style="width: fit-content;  "></el-input>
        </el-form-item>
 

        <el-form-item label="商品描述">
          <el-input   autosize type="textarea" v-model="form.description" style="width:400px;"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="dialogVisible = true">商品上架</el-button>
          <el-button @click="backHistory()">取消</el-button>
        </el-form-item>
      </el-form>


      <el-dialog
  title="提示"
  :visible.sync="dialogVisible"
  width="30%"
  >
  <span>确认上架这个商品</span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="onSubmit();dialogVisible = false">确 定</el-button>
  </span>
</el-dialog>


    </div>
  </div>
</template>
<script>
import {reqAddcommodity } from '../utils/request';
export default {
  data() {
    return {
      dialogVisible: false,
      form: {
        
        img: "",
        label: "",
        price: "",
        company: "",
        region: "",
        description: ""
      }
    };
  },
  methods: {
    onSubmit() {
    
      var label = this.form.label;
      var img = this.form.img;
      var price = this.form.price;
      var description = this.form.description;
      reqAddcommodity(label,img,price,description).then(res => {
            console.log(res);
            this.tableData = res.data;
    });
      console.log("submit!");
      this.$router.go(-1);//返回上一层
    },
    backHistory(){
    this.$router.go(-1);//返回上一层
  },
  }
};
</script>
<style>
.forms{
    margin-top: 8%;
}
</style>
