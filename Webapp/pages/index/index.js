// home.js
var wxCharts = require('../../utils/wxcharts-min.js');
var app = getApp();
//调用登录接口
app.getUserInfo();
var areaChart = null;

Page({
    data: {
      imgUrls: [
        '/style/images/1.jpg',
        '/style/images/2.jpg',
        '/style/images/3.jpg'
      ],
      indicatorDots: true,
      autoplay: true,
      interval: 3000,
      duration: 1000
    },
    touchHandler: function (e) {
        areaChart.showToolTip(e);
    },    
    onLoad: function (e) {
        //初始化信息
        var http = require("../../utils/httpUtil.js");
        var params = {
            id: 1 
        };
        var api = "WechatCustomer/";
        http.GET(api, params, function(res){
            //如果未成功请求微信服务器
            if (res.data == "error") {
                wx.showToast({
                    title: '请求失败',
                    icon: 'loading',
                    duration: 2000
                })
                wx.switchTab({
                  url: '/pages/login/index'
                })
            }
            //判断用户是否已绑定净水器
            if (res.data == false) {
                //跳转至登录界面
                wx.switchTab({
                  url: '/pages/login/index'
                })
            } else {
                app.info = res.data;
            }
        });
        
        var windowWidth = 320;
        try {
          var res = wx.getSystemInfoSync();
          windowWidth = res.windowWidth;
        } catch (e) {
          console.error('getSystemInfoSync failed!');
        }
        
        //折线图
        areaChart = new wxCharts({
            canvasId: 'areaCanvas',
            type: 'area',
            categories: ['02-20', '02-21', '02-22', '02-23', '02-24', '02-25'],
            animation: true,
            series: [{
                name: '用水量(升)',
                data: [32, 45, 45, 56, 33, 34],
                format: function (val) {
                    return val.toFixed(2) + '';
                }
            }],
            yAxis: {
                title: '',
                format: function (val) {
                    return val.toFixed(2);
                },
                min: 0,
                fontColor: '#8085e9',
                gridColor: '#8085e9',
                titleFontColor: '#f7a35c'
            },
            xAxis: {
                fontColor: '#7cb5ec',
                gridColor: '#7cb5ec'
            },
            extra: {
                legendTextColor: '#cb2431'
            },
            width: windowWidth,
            height: 200
        });
    }
});