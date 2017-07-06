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
          wx.getUserInfo({
            success: function (re) {
              that.globalData.userInfo = re.userInfo
              typeof cb == "function" && cb(that.globalData.userInfo)

              //判断是否获取登录凭证
              if (res.code) {
                //发送https请求，获取后续请求头信息和获取3rdsession
                var http = require("/utils/httpUtil.js");
                var params = {
                  code: res.code,
                  nickname: re.userInfo.nickName
                };
                var api = "Login/";
                http.GET(api, params, function(res){
                  //将x-auth-token存入缓存中
                  wx.setStorage({
                    key: "authToken",
                    data: res.header['x-auth-token']
                  })
                  //将3rdsession存入缓存中
                  wx.setStorage({
                    key: "threeRdSession",
                    data: res.data
                  });
                  
                });
              } else {
                console.log('获取用户登录态失败！' + res.errMsg)
              }
            }
          })
        }
      })
  }},

  globalData:{
    userInfo:null
  }
})