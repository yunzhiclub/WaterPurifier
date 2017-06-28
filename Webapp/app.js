//app.js
App({
  onLaunch: function () {
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
  },
  getUserInfo:function(cb){
    var that = this
    if(this.globalData.userInfo){
      typeof cb == "function" && cb(this.globalData.userInfo)
    }else{
      //调用登录接口
      wx.login({
        success: function (res) {
          console.log(3232);
          //发送https请求，最终目的是为了获取用户openid
          if (res.code) {
            console.log(232332);
            var http = require("/utils/httpUtil.js");
            var params = {
                code: res.code
            };
            var api = "Login/";
            
            http.GET(api, params, function(res){
                console.log(res.data);
            });
          } else {
            console.log('获取用户登录态失败！' + res.errMsg)
          }

          // wx.getUserInfo({
          //   success: function (res) {
          //     that.globalData.userInfo = res.userInfo
          //     typeof cb == "function" && cb(that.globalData.userInfo)
          //   }
          // })
        }
      })
    }
  },
  globalData:{
    userInfo:null
  }
})