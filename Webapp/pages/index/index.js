// home.js
var wxCharts = require('../../utils/wxcharts-min.js');
var app = getApp();
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
        //判断用户是否已绑定净水器
        
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