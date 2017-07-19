// 实例化全局应用
var app = getApp();
app.getUserInfo();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    //toast默认不显示  
    isShowToast: false  
  },
  bindNumber: function(e) {
    //用户输入的净水器编号
    app.number = e.detail.value;
  },
  //提示用户消息
  showToast: function () {  
      var self = this;  
      // toast时间  
      self.data.count = parseInt(self.data.count) ? parseInt(self.data.count) : 3000;  
      // 显示toast  
      self.setData({  
        isShowToast: true,  
      });  
      // 定时器关闭  
      setTimeout(function () {  
        self.setData({  
          isShowToast: false  
        });  
      }, self.data.count);  
    }, 
  /**
   * 跳转至首页
   */
  indexTap: function () {
    var self = this;
    //绑定净水器
    var http = require("../../utils/httpUtil.js");
    var params = {
      waterPurifierId: app.number,
      nickName: app.globalData.userInfo.nickName
    };
    var api = "WechatCustomer/bind";
    http.POST(api, params, function(res) {
      if (res.data.isExist == true) {
        //赋值净水器信息
        app.globalData.info = res.data.info;
        //跳转至首页
        wx.switchTab({
          url: '/pages/index/index'
        })
      } else {
        //友情提示用户
        self.setData({  
          count: 1500,  
          toastText: '对不起，不存在该净水器'  
        });  

        self.showToast(); 
      }
    })

    // 友情提示
    wx.showLoading({
      title: '加载中',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //友情提示用户，请求超时。todo：暂时小程序没有网络超时正确的提示，待完善
    if (options.fail == "1") {
      wx.showToast({
        title: '网络超时',
        icon: 'loading',
        duration: 3000
      })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})
