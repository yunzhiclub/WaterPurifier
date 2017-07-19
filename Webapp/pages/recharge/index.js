// index.js
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
      focus: false,
      inputValue: '',
      waterPurifierId: '',
      rechargeWaterQuantity: '',
      rechargeAmount: '',
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
      rechargeWaterQuantity: (e.detail.value * 10000).toString().format()
    })
    //更新金额
    self.setData({
      rechargeAmount: e.detail.value
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
  idInput: function(e) {
    this.setData({
      waterPurifierId: e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //更新净水器编号
    this.setData({
      waterPurifierId: app.globalData.info.id
    })
  
  },
  /**
   * 跳转至支付结果页面
   */
  resultTap: function () {
    var self = this;
    //获取请求参数
    var http = require("../../utils/httpUtil.js");
    var params = {
        waterPurifierId: parseInt(self.data.waterPurifierId),
        rechargeAmount: parseInt(self.data.rechargeAmount),
        rechargeWaterQuantity: parseInt(self.data.rechargeAmount * 10000)
    };
    console.log(params)
    var api = "WechatCustomer/getPaymentParams";
    http.POST(api, params, function(res){

      //用户确认支付
      wx.requestPayment({
         'timeStamp': res.data.timestamp,
         'nonceStr': res.data.nonceStr,
         'package': res.data._package,
         'signType': 'MD5',
         'paySign': res.data.paySign,
         'success':function(res){
          console.log(res);
         },
         'fail':function(res){
          console.log(res);
         }
      })

    });

    
    wx.navigateTo({
      url: '../result/index?rechargeAmount=' + parseInt(self.data.rechargeAmount)
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