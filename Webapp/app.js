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

              //发送https请求，最终目的是为了获取用户openid
              if (res.code) {
                //发送http请求，获取3rdsession
                var http = require("/utils/httpUtil.js");
                var params = {
                  code: res.code,
                  nickname: re.userInfo.nickName
                };
                var api = "Login/";
                http.GET(api, params, function(res){
                  console.log(res);
                  //将3rdsession存入缓存中
                  wx.setStorage({
                    key: re.userInfo.nickName,
                    data: res.data
                  });
                  //将threeRdSession/x-auth-token绑定到用户信息中
                  that.globalData.userInfo.threeRdSession = res.data;
                  that.globalData.userInfo.authToken = res.header['x-auth-token'];
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