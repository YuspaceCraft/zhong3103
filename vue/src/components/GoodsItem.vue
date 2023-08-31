<template>
  <div class="browser">
    <div class="head">
      <el-input
        placeholder="请输入内容"
        v-model="input1" clearable>
        <i slot="prefix" class="el-input__icon el-icon-search"></i>
      </el-input>
      <el-button type="primary" icon="el-icon-search" @click="fuzzysearch">搜索</el-button>
    </div>
    <!-- <router-link to="/goodsInfo"> -->
      <div class="grid">
          <div class="gridin" v-for="(item,index) in arr" :key="index"  @click="openCominfoPage(index, item)">
            <img :src="item.img" alt="">
            <span>{{ item.description }}</span>
          </div>
      </div>
    <!-- </router-link> -->
  </div>
</template>

<script>
import { reqgetcom } from '../utils/request.js'
import { reqfuzzySearch } from '../utils/request.js'
export default {
  mounted(){
    reqgetcom().then((res)=>{
      //console.log(res.data);
      // 如果需要设置或者修改data中的数据，那么获取方法务必是this.xxxx
      this.arr = res.data;
    })
  },
  data(){
    return{
      input1: '',
      arr: [],
    }
  },
  methods: {
    openCominfoPage(index, item){
      //console.log(`Clicked item ${index + 1}:`, item);
      this.$router.push({name: 'GoodsInfo',params: {params: item}});
    },
    fuzzysearch(){
      reqfuzzySearch(this.input1).then((res)=>{
            //console.log(res.data);
            this.arr = res.data;
        })
    }
  }
}
</script>

<style scoped>

.browser{
  display: flex;
  flex-direction: column;
}
.head{
  width: 500px;
  margin-top: 30px;
  display: flex;
}
.grid{
  margin: 10px;
  display: flex;
  /* 换行 */
  flex-wrap: wrap;
  /* 水平对齐方式 */
  justify-content:left;
}
.gridin{
  margin: 2px;
  margin-top: 80px;
  width: 230px;
  height: 230px;
  display: flex;
  flex-direction: column;
}

</style>