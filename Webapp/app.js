App({
  onLaunch: function () {
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    //检查session的是否过期
    wx.checkSession({
      success: function(){
        //session 未过期，并且在本生命周期一直有效
      },
      fail: function(){
        //登录态过期，调用登录接口
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
                  console.log(res)
                  console.log("fsdfsdfsdfs")
                  //判断是否成功获取openid
                  if ("" == res.data) {
                    console.log(2121);
                    //友情提示，并重启
                    wx.redirectTo({
                      url: '/pages/login/index?fail=1'
                    })
                  } else {
                    //将x-auth-token存入缓存中
                    wx.setStorageSync('authToken', res.header['x-auth-token']);
                    //将3rdsession存入缓存中
                    wx.setStorageSync('threeRdSession', res.data);
                  }              

                }
              })
            } else {
              console.log('获取用户登录态失败！' + res.errMsg)
            }
          }
        })
      }
    })

    this.getUserInfo();
    moneyFormat();
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
    userInfo:null,
    info:null
  }
  
})

//把表达金钱的数字格式化如1234567转为1,234,567
function moneyFormat() {
  String.prototype.format = function() {
    return this.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };
}
