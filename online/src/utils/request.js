// 引入axios
import axios from 'axios'
//获取商品信息
export const reqCommodity=()=>{
  return axios({
    url:'http://localhost:9005/commodity/allcommodity',
    method:'get'    
  })
}
//商品模糊查询
export const reqfind=(label)=>{
   const url = 'http://localhost:9005/commodity/findCommodity?label='+label;
    return axios.get(url);
}
//更新商品信息
export const reqUpdatecommodity=(label,img,price,description,id)=>{
    const url = 'http://localhost:9005/commodity/updateCommodity?label='+label+'&img='+img+'&price='+price+'&description='+description+'&id='+id;
    return axios.get(url);

}
//添加商品信息
export const reqAddcommodity=(label,img,price,description)=>{
  const url = 'http://localhost:9005/commodity/addCommodity?label='+label+'&img='+img+'&price='+price+'&description='+description;
  return axios.get(url);

}
//下架商品信息
export const reqDelcommodity=(id)=>{
  const url = 'http://localhost:9005/commodity/delCommodity?id='+id;
   return axios.get(url);
}


//获取订单信息
export const reqOrder=()=>{
  return axios({
    url:'http://localhost:9005/order/allorders',
    method:'get'    
  })
}

//订单模糊查询
export const reqfindorder=(label)=>{
  const url = 'http://localhost:9005/order/findorders?label='+label;
   return axios.get(url);
}

//删除订单信息
export const reqDelorder=(id)=>{
  const url = 'http://localhost:9005/order/delorders?id='+id;
   return axios.get(url);
}

//修改发货状态
export const requpdatesend=(id)=>{
  const url = 'http://localhost:9005/order/updatesend?id='+id;
   return axios.get(url);
}

//获取用户信息
export const requser=()=>{
  return axios({
    url:'http://localhost:9005/user/allUser',
    method:'get'    
  })
}
//模糊查询用户信息
export const reqfinduser=(username,phone,email,location,address)=>{
  const url = 'http://localhost:9005/user/finduserByConditions?username='+username+'&phone='+phone+'&email='+email+'&location='+location+'&address='+address;
  return axios.get(url);

}

//添加用户信息
export const reqAdduser=(username,password,phone,account,email,location,address)=>{
  const url = 'http://localhost:9005/user/adduser?username='+username+'&password='+password+'&phone='+phone+'&account='+account+
    '&email='+email+'&location='+location+'&address='+address;
  return axios.get(url);

}

//删除用户信息
export const reqDeluser=(id)=>{
  const url = 'http://localhost:9005/user/delUser?id='+id;
   return axios.get(url);
}
// 获取评论
export const reqgetcomment=(params)=>{
  return axios({
    url:'http://localhost:9005/user/comment/getcomment',
    method:'get',
    params:params
  })
}

//搜索评论
export const reqgetcommentById=(id)=>{
  const url = 'http://localhost:9005/user/comment/getcommentById?id='+id;
  return axios.get(url);
}
//增加评论
export const reqaddcomment=(id,username,comments)=>{
  const url = 'http://localhost:9005/user/comment/addcomment?id='+id+'&username='+username+'&comments='+comments;
  return axios.get(url);
}

//模糊商品评论查询
export const reqfuzzySearch=(fuzzystr)=>{
  const url = 'http://localhost:9005/commodity/fuzzy?fuzzystr='+fuzzystr;
  return axios.get(url);
}

//删除评论信息
export const reqDelcomment=(cid)=>{
  const url = 'http://localhost:9005/user/comment//deleteComment?cid='+cid;
   return axios.get(url);
}


//修改评论信息
export const requpdatecomment=(username,comments,cid)=>{
  const url = 'http://localhost:9005/user/comment/updateComment?username='+username+'&comments='+comments+'&cid='+cid;
   return axios.get(url);
}