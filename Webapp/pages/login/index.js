// 实例化全局应用
var app = getApp();

Page({
  /**
   * 页面的初始数据
   */
  data: {
  },
  bindNumber: function(e) {
    //用户输入的净水器编号
    app.number = e.detail.value;
  },
  /**
   * 跳转至首页
   */
  indexTap: function () {
    //跳转至首页
    wx.switchTab({
      url: '../index/index'
    });

    //发送http请求
    var http = require("../../utils/httpUtil.js");
    var params = {
        id: app.number
    };
    var api = "waterPurifier/WaterPurifier/";
    
    http.GET(api, params, function(res){
        console.log(res.data);
    });

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
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
