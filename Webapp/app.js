App({
  onLaunch: function () {
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    //调用登录接口
    wx.login({
      success: function (res) {
        //判断是否获取登录凭证
        if (res.code) {
          //发送https请求，获取后续请求头信息和获取3rdsession
          wx.request({
            url: 'https://api.water.mengyunzhi.com/Login/', //仅为示例，并非真实的接口地址
            data: {
               code: res.code,
            },
            header: {
                'content-type': 'application/json'
            },
            success: function(res) {
              //将x-auth-token存入缓存中
              wx.setStorageSync('authToken', res.header['x-auth-token']);
            
              //将3rdsession存入缓存中
              wx.setStorageSync('threeRdSession', res.data);
            }
          })
        } else {
          console.log('获取用户登录态失败！' + res.errMsg)
        }
      }
    })

    this.getUserInfo();
  },
  getUserInfo:function(cb){
    var that = this
    if(this.globalData.userInfo){
      typeof cb == "function" && cb(this.globalData.userInfo)
    }else{
      wx.getUserInfo({
        success: function (re) {
          that.globalData.userInfo = re.userInfo
          typeof cb == "function" && cb(that.globalData.userInfo)
        }
      })
  }},

  globalData:{
    userInfo:null
  }
})