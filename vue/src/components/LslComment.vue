<template>
    <div class="Comment">
      <div class="mainpage">
        <div class="header">
          <el-input placeholder="请输入内容" v-model="input" clearable>
          </el-input>
          <el-button type="primary" icon="el-icon-search" @click="search"
            >搜索</el-button
          >
        </div>
        <div
          class="commentItem"
          v-for="item in arr.slice(this.currentPage - 1, this.currentPage)"
        >
          <div class="orderInfo">
            <img :src="item.img" alt="" />
            <div class="orderInfoin">
              <p>{{ item.description }}</p>
              <p>月销量{{ item.soldnum }}</p>
              <p>价格: {{ item.price }}</p>
              <el-rate v-model="value" show-text> </el-rate>
            </div>
      
            <div class="button">
              <el-badge :value="null" class="item">
                <el-button
                  type="primary"
                  icon="
                el-icon-chat-line-round"
                  circle
                  @click="getuserinaddcomment()"
                ></el-button>
                
                <!-- 添加评论 -->
                <el-dialog title="发表评论" :visible.sync="dialogFormVisible">
                  <el-form>
                    <el-form-item label="用户名">
                      <!-- <p> {{ selectusername }}</p> -->
                      <el-select
                        clearable
                        v-model="selectusername"
                        placeholder="请选择用户"
                      >
                        <el-option
                          v-for="item in selectUserOptions"
                          :key="item.id"
                          :value="item.username"
                          >{{ item.username }}</el-option
                        >
                      </el-select>
                    </el-form-item>
                    <el-form-item label="我的评论">
                      <el-input
                        v-model="mycomments"
                        autocomplete="off"
                        clearable
                      ></el-input>
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
          <el-divider></el-divider>
          <div class="comments">
            <div class="comments-container">
              <div class="incomments" v-for="item in currentcomments">
               
                <div>
                   <h3>{{ item.username }}:</h3>
                <p>{{ item.comments }}</p>
  
                <el-input v-if="false" v-model="item.cid"></el-input>
                <el-row>
                  <el-button
                    type="primary"
                    icon="el-icon-edit"
                    circle
                    @click="getusercomment(item)"
                  ></el-button>
                  <el-button
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    @click="deletecomment(item.cid)"
                    style="margin-right:20px ;"
                  ></el-button>
                </el-row><el-divider></el-divider>
                </div>
                
              </div>
              
  
              <el-dialog
                title="评论修改"
                :visible.sync="dialogFormVisiblecomment"
              >
                <el-form :model="form">
  
                  <el-form-item  v-if="false" label="评论号" :label-width="formLabelWidth">
                    <el-input
                      :readonly="true"
                      v-model="form.cid"
                      autocomplete="off"
                    ></el-input>
                    </el-form-item>
  
                  <el-form-item label="用户名" :label-width="formLabelWidth">
                    <el-input
                      :readonly="true"
                      v-model="form.username"
                      autocomplete="off"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="用户评论" :label-width="formLabelWidth">
                    <el-input
                      v-model="form.comment"
                      autocomplete="off"
                    ></el-input>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisiblecomment = false"
                    >取 消</el-button
                  >
                  <el-button
                    type="primary"
                    @click="updatecomments()"
                    >确 定</el-button
                  >
                </div>
              </el-dialog>
            </div>
          </div>
        </div>
        <div class="bottom">
          <p>当前页数: {{ currentPage }},总页数为: {{ totalPge }}</p>
          <el-button-group>
            <el-button type="primary" icon="el-icon-arrow-up" @click="startpage"
              >首页</el-button
            >
            <el-button type="primary" icon="el-icon-arrow-left" @click="prepage"
              >上一页</el-button
            >
            <el-button type="primary" @click="nextpage"
              >下一页<i class="el-icon-arrow-right el-icon--right"></i
            ></el-button>
            <el-button type="primary" icon="el-icon-arrow-up" @click="endpage"
              >末页</el-button
            >
          </el-button-group>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { requser } from "../utils/request.js";
  import { reqfuzzySearch } from "../utils/request.js";
  import { reqCommodity } from "../utils/request.js";
  import { reqgetcomment } from "../utils/request.js";
  import { reqgetcommentById } from "../utils/request.js";
  import { reqaddcomment } from "../utils/request.js";
  import { reqDelcomment } from "../utils/request.js";
  import { requpdatecomment } from "../utils/request.js";
  
  export default {
    mounted() {
      reqCommodity().then(res => {
        //console.log(res.data);
        this.arr = res.data;
        this.totalPge = res.data.length;
      });
      requser().then(res => {
        //console.log(res.data);
       
      });
      reqgetcomment().then(res => {
        //console.log(res.data);
        this.allcomments = res.data;
      });
      reqgetcommentById(this.currentPage).then(res => {
        //console.log(res.data);
        this.currentcomments = res.data;
      });
    },
    data() {
      return {
        selectUserOptions: [],
        dialogFormVisiblecomment: false,
        cid: "",
        selectusername: "",
        arr: [],
        allcomments: [],
        currentcomments: [],
        comid: 1,
        value: null,
        input: "",
        pageStart: 1,
        currentPage: 1,
        totalPge: 5,
        dialogFormVisible: false,
        username: "",
        mycomments: "",
  
        form: {
          username: "",
          comment: "",
          cid:""
        },
        formLabelWidth: "120px"
      };
    },
    methods: {
      prepage() {
        if (this.currentPage == 1) {
          //第一页
          this.$message({
            message: "当前已在第一页",
            type: "warning"
          });
        } else {
          this.currentPage--;
          reqgetcommentById(this.currentPage).then(res => {
            //console.log(res.data);
            this.currentcomments = res.data;
          });
        }
      },
      nextpage() {
        if (this.currentPage == this.totalPge) {
          //最后一页
          this.$message({
            message: "当前已在最后一页",
            type: "warning"
          });
        } else {
          this.currentPage++;
          reqgetcommentById(this.currentPage).then(res => {
            //console.log(res.data);
            this.currentcomments = res.data;
          });
        }
      },
      startpage() {
        this.currentPage = 1;
        reqgetcommentById(this.currentPage).then(res => {
          //console.log(res.data);
          this.currentcomments = res.data;
        });
      },
      endpage() {
        this.currentPage = this.totalPge;
        reqgetcommentById(this.currentPage).then(res => {
          //console.log(res.data);
          this.currentcomments = res.data;
        });
      },
      close1() {
        // const loading = this.$loading({
        //   lock: true,
        //   text: "Loading",
        //   spinner: "el-icon-loading",
        //   background: "rgba(0, 0, 0, 0.7)"
        // });
        // setTimeout(() => {
        //   loading.close();
         
        // }, 100);
        this.mycomments = "";
        this.dialogFormVisible = false;
      },
      confirm1() {
        const loading = this.$loading({
          lock: true,
          text: "Loading",
          spinner: "el-icon-loading",
          background: "rgba(0, 0, 0, 0.7)"
        });
        reqaddcomment(this.currentPage, this.selectusername, this.mycomments).then(
          res => {
            //console.log(res.data);
          }
        );
        setTimeout(() => {
          loading.close();
          reqgetcommentById(this.currentPage).then(res => {
            //console.log(res.data);
            this.currentcomments = res.data;
          });
          this.mycomments = "";
          this.$message({
            type: "success",
            message: "发布成功!"
          });
        }, 100);
        this.dialogFormVisible = false;
      },
      search() {
        const loading = this.$loading({
          lock: true,
          text: "Loading",
          spinner: "el-icon-loading",
          background: "rgba(0, 0, 0, 0.7)"
        });
        reqfuzzySearch(this.input).then(res => {
          //console.log(res.data);
          this.currentPage = res.data[0].id;
        });
        setTimeout(() => {
          loading.close();
          reqgetcommentById(this.currentPage).then(res => {
            //console.log(res.data);
            this.currentcomments = res.data;
          });
        }, 100);
      },
  
      deletecomment(cid) {
        const loading = this.$loading({
          lock: true,
          text: "Loading",
          spinner: "el-icon-loading",
          background: "rgba(0, 0, 0, 0.7)"
        });
        reqDelcomment(cid).then(res => {
          //console.log(res.data);
        });
        setTimeout(() => {
          loading.close();
          reqgetcommentById(this.currentPage).then(res => {
            //console.log(res.data);
            this.currentcomments = res.data;
          });
  
          this.$message({
            type: "success",
            message: "删除成功!"
          });
        }, 100);
      },
  
      getusercomment(item){
       this.dialogFormVisiblecomment = true;
       this.form.username = item.username;
       this.form.comment = item.comments;
       this.form.cid = item.cid;
  
      },
  
      updatecomments() {
        const loading = this.$loading({
          lock: true,
          text: "Loading",
          spinner: "el-icon-loading",
          background: "rgba(0, 0, 0, 0.7)"
        });
        
        requpdatecomment(this.form.username, this.form.comment, this.form.cid).then(res => {
          //console.log(res.data);
        });
        setTimeout(() => {
          loading.close();
          reqgetcommentById(this.currentPage).then(res => {
            //console.log(res.data);
            this.currentcomments = res.data;
          });
  
          this.$message({
            type: "success",
            message: "修改成功!"
          });
        }, 100);
        this.dialogFormVisiblecomment = false
      },
  
  
  
      getuserinaddcomment(){
        this.dialogFormVisible = true 
        requser().then(res => {
      console.log(res.data);
        this.selectUserOptions = res.data;
  
      
      });
      }
    }
  };
  </script>
  
  <style scoped>
  .item {
    margin-top: 46px;
    margin-right: 40px;
  }
  .Comment {
    display: flex;
    height: 670px;
    justify-content: center;
  }
  .mainpage {
    display: flex;
    flex-direction: column;
  
    background-color: rgb(255, 255, 255);
    border: 20px;
    width: 950px;
    height: 675px;
  }
  .mainpage .bottom {
    display: flex;
    align-items: center;
    justify-content: flex-end;
  }
  .mainpage .button {
    display: flex;
    justify-content: flex-end;
  }
  .header {
    width: 400px;
    margin-left: 0px;
    margin-top: 20px;
    display: flex;
  }
  .header el-input {
    width: 100px;
  }
  .commentItem {
    margin-top: 20px;
    margin-left: 0px;
  }
  .orderInfo {
    display: flex;
    justify-content: flex-start;
  }
  .orderInfoin {
    margin-left: 20px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }
  .orderInfo img {
    width: 100px;
    height: 100px;
  }
  .comments {
    background-color: rgb(255, 255, 255);
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
  }
  .comments-container {
    max-height: 400px; /* 设置容器最大高度 */
    overflow-y: auto; /* 设置垂直滚动 */
  }
  .incomments {
    display: flex;
    margin: 20px;
  }
  .incomments p {
    margin-left: 10px;
  }
  </style>