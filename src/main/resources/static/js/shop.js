'use strict';
const url = {
  root: "/",
  login: "/login",
  logout: "/logout",
  tables:["/menu/","/item/"],
  findAll(tab){
    switch(tab){
      case 0:return this.tables[tab]+"CustomView/findAll";
      case 1:return this.tables[tab]+"CustomView/cart";
      case 2:return this.tables[1]+"CustomView/history";
    }
  },
  findOne(tab){
    return this.tables[tab];
  },
  save(tab){
    return this.tables[tab]+"save";
  },
  remove(tab){
    return this.tables[tab]+"remove/"
  },
  buy(id){
    return "/menu/buy/"+id;
  },
  clearCart(){
    return this.tables[1]+"clearCart";
  },
  pay(id){
    return this.tables[1]+"pay";
  },
}
const data = {
  user: {
    username: getCookie("username")===false?"":getCookie("username"),
  },
  list: {},
  entity: {},
  show: {
    tab: 0,
    func: 'list',
  },
  titles: [
    ["id","名称","价格(元)","库存","备注","购买数量"],
    ["id","商品名","数量","价格","总价","订单状态","更新时间"],
    ["订单编号","名称","数量","单价","总金额","订单状态","更新时间"],
  ]
}
// 获取url中的值
function getUrl(key) {
  let vars = window.location.search.substring(1).split('&');
  return getVariable(vars, key);
}
// 获取cookie中的值
function getCookie(key) {
  let vars = document.cookie.split('; ');
  return getVariable(vars, key);
}
// 获取字符串中的参数
function getVariable(vars, key) {
  for (let i = 0; i < vars.length; i++) {
    let pair = vars[i].split("=");
    if (pair[0] === key) {
      return pair[1];
    }
  }
  return false;
}
// @description 将变量集合编码为&开头的字符串
function urlEncoding(object) {
  let ret = '';
  for (let i in object) {
    if (eval('object.' + i) !== null && eval('object.' + i) !== undefined && eval('object.' + i) !== '') {
      ret = ret + '&' + i + '=' + eval("object." + i);
    }
  }
  return ret;
}
 //将变量集合编码为?开头的字符串
function urlEncoding2(object) {
  let ret = '';
  for (let i in object) {
    if (eval('object.' + i) !== null && eval('object.' + i) !== undefined && eval('object.' + i) !== '') {
      ret = ret + '&' + i + '=' + eval("object." + i);
    }
  }
  ret = ret.replace('&', '?');
  return ret;
}
