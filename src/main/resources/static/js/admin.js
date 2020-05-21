'use strict';
const url = {
  root: "/",
  login: "/login",
  logout: "/logout",
  tables:["/user/","/menu/","/item/"],
  findAll(tab){
    return this.tables[tab]+"listView/findAll";
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
  setActive(tab,id){
    return this.tables[tab]+"setActive/"+id;
  },
  setNotActive(tab,id){
    return this.tables[tab]+"setNotActive/"+id;
  },
  cancelItem(id){
    return this.tables[2]+"cancel/"+id;
  },
  backMoney(id){
    return this.tables[2]+"backMoney/"+id;
  },
  finished(id){
    return this.tables[2]+"finished/"+id;
  }
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
    ["id", "用户名", "姓名", "性别", "年龄", "角色"],
    ["id","名称","价格","备注","库存","状态"],
    ["订单编号","下单用户","商品名","数量","总金额","类型","更新时间"],
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
