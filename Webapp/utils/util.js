function formatTime(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()


  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function formatNumber(n) {
  n = n.toString()
  return n[1] ? n : '0' + n
}

module.exports = {
  formatTime: formatTime
}

//使用promise
var Promise = require('bluebird.min')

function wxPromisify(fn) {  
  return function (obj = {}) {    
    return new Promise((resolve, reject) => {      
      obj.success = function (res) {        
        resolve(res)      
      }      

      obj.fail = function (res) {        
        reject(res)      
      }      

      fn(obj)    
    })  
  }
}

module.exports = {  
  wxPromisify: wxPromisify
}
// var util = require('../../utils/util')
// var t = util.wxPromisify(app.getUserInfo);
// t().then(test1());