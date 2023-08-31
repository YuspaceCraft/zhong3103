<template>
  <div class="Comment">
    <div class="mainpage">
      <div class="header">
        <el-input
          placeholder="请输入内容"
          v-model="input"
          clearable>
        </el-input>
        <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
      </div>
      <div class="commentItem" v-for="item in arr.slice(this.currentPage-1,this.currentPage)">
        <div class="orderInfo">
          <img :src="item.img" alt="">
          <div class="orderInfoin">
            <p>{{item.description}}</p>
            <p>月销量{{item.soldnum}}</p>
            <p>价格: {{item.price}}</p>
            <el-rate
              v-model="value"
              show-text>
            </el-rate>
          </div>
          <div class="button">
            <el-badge :value="null" class="item">
              <el-button type="primary" icon="
              el-icon-chat-line-round" circle @click="dialogFormVisible = true"></el-button>
              <el-dialog title="发表评论" :visible.sync="dialogFormVisible">
                <el-form>
                  <p>用户名: {{username}}</p>
                  <el-form-item label="我的评论">
                    <el-input v-model="mycomments" autocomplete="off" clearable></el-input>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="close1">取 消</el-button>
                  <el-button type="primary" @click="confirm1">确 定</el-button>
                </div>
              </el-dialog>
            </el-badge> 
          </div>
        </div>
        <div class="comments">
          <div class="incomments" v-for="item in currentcomments">
            <h3>{{item.username}}:</h3>
            <p>{{item.comments}}</p>
          </div>
        </div>
      </div>
      <div class="bottom">
        <p>当前页数: {{currentPage}},总页数为: {{totalPge}}</p>
        <el-button-group>
          <el-button type="primary" icon="el-icon-arrow-up" @click="startpage">首页</el-button>
          <el-button type="primary" icon="el-icon-arrow-left" @click="prepage">上一页</el-button>
          <el-button type="primary"  @click="nextpage">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
          <el-button type="primary" icon="el-icon-arrow-up" @click="endpage">末页</el-button>
        </el-button-group>
      </div>
    </div>
  </div>
</template>

<script>
import { reqgetuser } from '../utils/request.js'
import { reqfuzzySearch } from '../utils/request.js'
import { reqgetcom } from '../utils/request.js'
import { reqgetcomment } from '../utils/request.js'
import { reqgetcommentById } from '../utils/request.js'
import { reqaddcomment } from '../utils/request.js'
export default {
  mounted(){
    reqgetcom().then((res)=>{
      //console.log(res.data);
      this.arr = res.data;
      this.totalPge = res.data.length;
    })
    reqgetuser().then((res)=>{
      //console.log(res.data);
      this.username = res.data[0].username;
    })
    reqgetcomment().then((res)=>{
      //console.log(res.data);
      this.allcomments = res.data;
    })
    reqgetcommentById(this.currentPage).then((res)=>{
      //console.log(res.data);
      this.currentcomments = res.data;
    })
  },
  data(){
    return{
      arr:[],
      allcomments: [],
      currentcomments:[],
      comid: 1,
      value: null,
      input: '',
      pageStart: 1,
      currentPage: 1,
      totalPge: 5,
      dialogFormVisible: false,
      username: '',
      mycomments: '',
    }
  },
  methods: {
    prepage(){
      if(this.currentPage==1){
        //第一页
        this.$message({
          message: '当前已在第一页',
          type: 'warning'
        });
      }
      else{
        this.currentPage--;
        reqgetcommentById(this.currentPage).then((res)=>{
          //console.log(res.data);
          this.currentcomments = res.data;
        })
      }
    },
    nextpage(){
      if(this.currentPage==this.totalPge){
        //最后一页
        this.$message({
          message: '当前已在最后一页',
          type: 'warning'
        });
      }
      else{
        this.currentPage++;
        reqgetcommentById(this.currentPage).then((res)=>{
          //console.log(res.data);
          this.currentcomments = res.data;
        })
      }
    },
    startpage(){
      this.currentPage = 1;
      reqgetcommentById(this.currentPage).then((res)=>{
          //console.log(res.data);
          this.currentcomments = res.data;
        })
    },
    endpage(){
      this.currentPage = this.totalPge;
      reqgetcommentById(this.currentPage).then((res)=>{
          //console.log(res.data);
          this.currentcomments = res.data;
        })
    },
    close1(){
      const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
      setTimeout(() => {
        loading.close();
        this.$message('取消评论');
      }, 500);
      this.mycomments = '';
      this.dialogFormVisible = false
    },
    confirm1(){
      const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        reqaddcomment(this.currentPage,this.username,this.mycomments).then((res)=>{
          //console.log(res.data);
        })
      setTimeout(() => {
        loading.close();
        reqgetcommentById(this.currentPage).then((res)=>{
          //console.log(res.data);
          this.currentcomments = res.data;
        })
        this.mycomments = '';
        this.$message({
          type: 'success',
          message: '发布成功!'
        });
      }, 2000);
      this.dialogFormVisible = false
    },
    search(){
      const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
      reqfuzzySearch(this.input).then((res)=>{
            //console.log(res.data);
            this.currentPage =res.data[0].id;
      })
      setTimeout(() => {
        loading.close();
        reqgetcommentById(this.currentPage).then((res)=>{
        //console.log(res.data);
        this.currentcomments = res.data;
      })
      }, 500);
      
    }
  },
  
}
</script>

<style scoped>
.item {
  margin-top: 10px;
  margin-right: 40px;
}
.Comment{
  display: flex;
  justify-content: center;
}
.mainpage{
  display: flex;
  flex-direction: column;
  background-color: rgb(243, 255, 162);
  border: 20px;
  width: 950px;
  height: 710px;
}
.mainpage .bottom{
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.mainpage .button{
  display: flex;
  justify-content:flex-end;
}
.header{
  width: 400px;
  margin-left: 80px;
  margin-top: 20px;
  display: flex;
}
.header el-input{
  width: 100px;
}
.commentItem{
  margin-top: 50px;
  margin-left: 80px;
}
.orderInfo{
  display: flex;
  justify-content:flex-start;
}
.orderInfoin{
  margin-left: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.orderInfo img{
  width: 100px;
  height: 100px;
}
.comments{
  background-color: aquamarine;
  display: flex;
  flex-direction: column;
  justify-content:flex-start;
}
.incomments{
  display: flex;
  margin: 20px;
}
.incomments p{
  margin-left: 10px;
}
</style>