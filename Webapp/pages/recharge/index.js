// index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      focus: false,
      inputValue: '',
      disabled: true
  },
  rechageInput: function(e) {
    var self = this;
    //如果用户输入的数字是以零开头的，则不更新水量。如00232
    if (e.detail.value.substr(0, 1) == 0) {
      e.detail.value = 0;
    }
    //更新水量
    self.setData({
      waterValue: e.detail.value * 10
    })
    //disabled微信支付按钮
    if (e.detail.value == '' || e.detail.value.substr(0, 1) == 0) {
      self.setData({
        disabled: true
      })
    } else {
      self.setData({
        disabled: false
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },
  /**
   * 跳转至支付结果页面
   */
  resultTap: function () {
    wx.requestPayment({
       'timeStamp': '',
       'nonceStr': '',
       'package': '',
       'signType': 'MD5',
       'paySign': '',
       'success':function(res){
       },
       'fail':function(res){
       }
    })
    wx.navigateTo({
      url: '../result/index'
    })
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