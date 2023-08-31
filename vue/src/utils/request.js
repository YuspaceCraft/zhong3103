// 引入axios
import axios from 'axios'

export const reqgetcom=(params)=>{
  return axios({
    url:'/api/commodity/test',
    method:'get',
    params:params
  })
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

