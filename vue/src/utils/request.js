// 引入axios
import axios from 'axios'

export const reqgetcom=(params)=>{
  return axios({
    url:'/api/commodity/test',
    method:'get',
    params:params
  })
}

export const reqfindBycommId=(id)=>{
  const url = '/api/commodity/findBycommId?id='+id;
  return axios.get(url);
}

export const reqgetorders=(params)=>{
  return axios({
    url:'/api/order/getorders',
    method:'get',
    params:params
  })
}

export const reqaddtocart=(id,img,label,price,num)=>{
  const url = '/api/order/addtocart?id='+id+'&img='+img+'&label='+label+'&price='+price+'&num='+num;
  return axios.get(url);
}

export const reqdeletecart=(id)=>{
  const url = '/api/order/deletecart?id='+id;
  return axios.get(url);
}

export const requpdatecart=(id,num)=>{
  const url = '/api/order/updatecart?id='+id+'&num='+num;
  return axios.get(url);
}

export const reqgetuser=(params)=>{
  return axios({
    url:'/api/jifen/getuser',
    method:'get',
    params:params
  })
}

export const requpdateuser=(username,password,location,phone,account,email,address)=>{
  const url = '/api/jifen/updateuser?username='+username+'&password='+password
              +'&location='+location+'&phone='+phone+'&account='+account+'&email='+email+'&address='+address;
  return axios.get(url);
}

export const reqadduser=(username,password,location,phone,account,email,address)=>{
  const url = '/api/jifen/adduser?username='+username+'&password='+password
              +'&location='+location+'&phone='+phone+'&account='+account+'&email='+email+'&address='+address;
  return axios.get(url);
}

export const reqbuything=(account)=>{
  const url = '/api/jifen/buything?account='+account;
  return axios.get(url);

}

export const reqfuzzySearch=(fuzzystr)=>{
  const url = '/api/commodity/fuzzy?fuzzystr='+fuzzystr;
  return axios.get(url);
}


export const reqgetcomment=(params)=>{
  return axios({
    url:'/api/jifen/getcomment',
    method:'get',
    params:params
  })
}
export const reqgetcommentById=(id)=>{
  const url = '/api/jifen/getcommentById?id='+id;
  return axios.get(url);
}

export const reqaddcomment=(id,username,comments)=>{
  const url = '/api/jifen/addcomment?id='+id+'&username='+username+'&comments='+comments;
  return axios.get(url);
}

export const reqaddbrowse=(id,label,price,soldnum,description)=>{
  const url = '/api/jifen/addbrowse?id='+id+'&label='+label+'&price='+price+'&soldnum='+soldnum+'&description='+description;
  return axios.get(url);
}

export const reqaddorder1=(orderid,date,name,address,num,price,comment)=>{
  const url = '/api/jifen/addorder1?orderid='+orderid+'&date='+
  date+'&name='+name+'&address='+address+'&num='+num+'&price='+price+'&comment='+comment;
  return axios.get(url);
}


//lsl
export const reqCommodity=()=>{
  return axios({
    url:'/api/commodity/allcommodity',
    method:'get'    
  })
}
//商品模糊查询
export const reqfind=(label)=>{
   const url = '/api/commodity/findCommodity?label='+label;
    return axios.get(url);
}
//更新商品信息
export const reqUpdatecommodity=(label,img,price,description,id)=>{
    const url = '/api/commodity/updateCommodity?label='+label+'&img='+img+'&price='+price+'&description='+description+'&id='+id;
    return axios.get(url);

}
//添加商品信息
export const reqAddcommodity=(label,img,price,description)=>{
  const url = '/api/commodity/addCommodity?label='+label+'&img='+img+'&price='+price+'&description='+description;
  return axios.get(url);

}
//下架商品信息
export const reqDelcommodity=(id)=>{
  const url = '/api/commodity/delCommodity?id='+id;
   return axios.get(url);
}


//获取订单信息
export const reqOrder=()=>{
  return axios({
    url:'/api/order/allorders',
    method:'get'    
  })
}

//订单模糊查询
export const reqfindorder=(label)=>{
  const url = '/api/order/findorders?label='+label;
   return axios.get(url);
}

//删除订单信息
export const reqDelorder=(id)=>{
  const url = '/api/order/delorders?id='+id;
   return axios.get(url);
}

//修改发货状态
export const requpdatesend=(id)=>{
  const url = '/api/order/updatesend?id='+id;
   return axios.get(url);
}

//获取用户信息
export const requser=()=>{
  return axios({
    url:'/api/jifen/allUser',
    method:'get'    
  })
}
//模糊查询用户信息
export const reqfinduser=(username,phone,email,location,address)=>{
  const url = '/api/jifen/finduserByConditions?username='+username+'&phone='+phone+'&email='+email+'&location='+location+'&address='+address;
  return axios.get(url);

}

//添加用户信息
export const reqAdduser=(username,password,phone,account,email,location,address)=>{
  const url = '/api/jifen/adduser?username='+username+'&password='+password+'&phone='+phone+'&account='+account+
    '&email='+email+'&location='+location+'&address='+address;
  return axios.get(url);

}

//删除用户信息
export const reqDeluser=(id)=>{
  const url = '/api/jifen/delUser?id='+id;
   return axios.get(url);
}

//删除评论信息
export const reqDelcomment=(cid)=>{
  const url = '/api/jifen/deleteComment?cid='+cid;
   return axios.get(url);
}


//修改评论信息
export const requpdatecomment=(username,comments,cid)=>{
  const url = '/api/jifen/updateComment?username='+username+'&comments='+comments+'&cid='+cid;
   return axios.get(url);
}



//zrz
// 分别设置我要请求的接口
export const reqData=()=>{
  return axios({
    url:'/api/jifen/findall',
    method:'get',
  })
}

// 模糊搜索功能
export const reqSearch=(params)=>{
  return axios({
    url:'/api/jifen/dimfind',
    method:'get',
    params:params
  })
}

// 删除功能
export const reqDelete=(params)=>{
  return axios({
    url:'/api/jifen/delete',
    method:'get',
    params:params
  })
}

// 获取评论功能
export const reqcomment=(params1)=>{
  // return axios({
  //   url:'/api/equipment/update',
  //   method:'get',
  //   params:params1,params2
  // })
  const url = '/api/jifen/getcomment?orderid='+params1;
  return axios.get(url);
}

// 更新评论功能
export const requpdate=(params1,params2)=>{
  const url = '/api/jifen/updatecomment?orderid='+params1+'&comment='+params2;
  return axios.get(url);
}

export const reqlogin=(params1,params2)=>{
  // return axios({
  //   url:'/api/login/loginverify',
  //   method:'get',
  //   params:params
  // })
  const url = '/api/jifen/loginverify?username='+params1+'&password='+params2;
  return axios.get(url);
}

export const reqBrowse=()=>{
  // return axios({
  //   url:'/api/login/loginverify',
  //   method:'get',
  //   params:params
  // })
  const url = '/api/jifen/browse';
  return axios.get(url);
}

export const reqfinduser1=(params1)=>{
  // return axios({
  //   url:'/api/login/loginverify',
  //   method:'get',
  //   params:params
  // })
  const url = '/api/jifen/finduser?id='+params1;
  return axios.get(url);
}

export const requpdateuser1=(params,params1,params2,params3,params4,params5)=>{
  // return axios({
  //   url:'/api/login/loginverify',
  //   method:'get',
  //   params:params
  // })
  const url = '/api/jifen/updateuser1?id='+params+'&username='+params1+'&password='+params2+'&age='+params3+'&phone='+params4+'&address='+params5;
  return axios.get(url);
}

export const reqId=()=>{
  return axios({
    url:'/api/jifen/returnid',
    method:'get',
  })
}
