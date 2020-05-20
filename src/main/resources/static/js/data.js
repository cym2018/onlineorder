'use strict';
const url = {
  root: "/",
  login: "/login",
  logout: "/logout",
  tables:["/user/","/menu/","/order/"],
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
    ["id","名称","价格","备注","类型","库存","状态"]
  ]
}
// 通用
const page = {
  "_currPage": 0,
  "_showPage": 1,
  "totalPage": 0,
  "totalRow": 0,
  "pageSize": 20,
  set currPage(val) {
    if (val >= 0 && val < this.totalPage) {
      this._currPage = val;
      this._showPage = val + 1;
    }
  },
  set showPage(val) {
    this._showPage = val;
    this._currPage = val - 1;
  },
  get showPage() {
    return this._showPage;
  },
  get currPage() {
    return this._currPage;
  },
  get firstRow() {
    return this.pageSize * this._currPage + 1;
  },
  get lastRow() {
    if (this.pageSize * (this._currPage + 1) > this.totalRow)
      return this.totalRow;
    return this.pageSize * (this._currPage + 1);
  },
};

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

/**
 * @description 将变量集合编码为&开头的字符串
 * @param object 变量集合
 * @returns {string} url
 */
function urlEncoding(object) {
  let ret = '';
  for (let i in object) {
    if (eval('object.' + i) !== null && eval('object.' + i) !== undefined && eval('object.' + i) !== '') {
      ret = ret + '&' + i + '=' + eval("object." + i);
    }
  }
  return ret;
}

/**
 * @description 将变量集合编码为?开头的字符串
 * @param object 变量集合
 * @returns {string} url
 */
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
