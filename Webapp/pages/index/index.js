// 引用wxchart
var wxCharts = require('../../style/js/wxcharts-min.js');
var areaChart = null;
var app = getApp();

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
      duration: 1000,
      todayUsedWater: 0,
      lastUsedWater: 0,
      lastFilterChip: 0,
      usedAfterWaterQuality: 0
    },
    touchHandler: function (e) {
        areaChart.showToolTip(e);
    },    
    onLoad: function (e) {
        var self = this;
        //获取最近一周用水量
        
        //初始化信息，发送请求
        var http = require("../../utils/httpUtil.js");
        var params = {
            id: 1 
        };
        var api = "WechatCustomer/isBind";
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
            console.log(res.data);
            //判断用户是否已绑定净水器
            if (res.data == false) {

                //跳转至登录界面
                wx.redirectTo({
                  url: '/pages/login/index'
                })
            } else {
                app.info = res.data;
                //更新视图
                self.setData({
                    todayUsedWater: app.info.todayUsedWater.toString().format(),
                    lastUsedWater: app.info.lastUsedWater.toString().format(),
                    lastFilterChip: app.info.lastFilterChip,
                    usedAfterWaterQuality: app.info.usedAfterWaterQuality
                })
                //设置wxchart数据更新
                app.chart(res.data.recentOneWeekUsedWater);
            }

        });

        
    }
});

//设置wxchart数据更新
app.chart = function chart(value) {
    //获取屏幕宽度
    try {
      var res = wx.getSystemInfoSync();
      var windowWidth = res.windowWidth;
    } catch (e) {
      console.error('getSystemInfoSync failed!');
    }
    //对获取的日期格式做修改，如2017-05-03改为05-03
    var keys = [];
    Object.keys(value).forEach(function (key) {
        keys.push(key.substring(5, 10));
    });
    //获取每天的用水量
    var datas = [];
    Object.keys(value).forEach(function (key) {
        datas.push(value[key]);
    });
    //折线图
    areaChart = new wxCharts({
        canvasId: 'areaCanvas',
        type: 'area',
        categories: keys,
        animation: true,
        series: [{
            name: '用水量(升)',
            data: datas,
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

