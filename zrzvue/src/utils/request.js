// 引入axios
import axios from 'axios'


// 分别设置我要请求的接口
export const reqData=()=>{
  return axios({
    url:'/api/order/findall',
    method:'get',
  })
}

// 模糊搜索功能
export const reqSearch=(params)=>{
  return axios({
    url:'/api/order/dimfind',
    method:'get',
    params:params
  })
}

// 删除功能
export const reqDelete=(params)=>{
  return axios({
    url:'/api/order/delete',
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
  const url = '/api/order/getcomment?orderid='+params1;
  return axios.get(url);
}

// 更新评论功能
export const requpdate=(params1,params2)=>{
  const url = '/api/order/updatecomment?orderid='+params1+'&comment='+params2;
  return axios.get(url);
}

export const reqlogin=(params1,params2)=>{
  // return axios({
  //   url:'/api/login/loginverify',
  //   method:'get',
  //   params:params
  // })
  const url = '/api/login/loginverify?username='+params1+'&password='+params2;
  return axios.get(url);
}

export const reqBrowse=()=>{
  // return axios({
  //   url:'/api/login/loginverify',
  //   method:'get',
  //   params:params
  // })
  const url = '/api/onlineshop/browse';
  return axios.get(url);
}

export const reqfinduser=(params1)=>{
  // return axios({
  //   url:'/api/login/loginverify',
  //   method:'get',
  //   params:params
  // })
  const url = '/api/login/finduser?id='+params1;
  return axios.get(url);
}

export const requpdateuser=(params,params1,params2,params3,params4,params5)=>{
  // return axios({
  //   url:'/api/login/loginverify',
  //   method:'get',
  //   params:params
  // })
  const url = '/api/login/updateuser?id='+params+'&username='+params1+'&password='+params2+'&age='+params3+'&phone='+params4+'&address='+params5;
  return axios.get(url);
}

export const reqId=()=>{
  return axios({
    url:'/api/login/returnid',
    method:'get',
  })
}

