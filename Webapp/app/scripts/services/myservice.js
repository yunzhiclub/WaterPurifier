'use strict';

/**
 * @ngdoc service
 * @name webappApp.myService
 * @description
 * # myService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('myService', function () {
    	if (id === '1') {
            $scope.title = "今日用水情况";
            $scope.datas = [
                [{
                    quantity: "20升",
                    date: new Date()
                }],
            ];
        } else if (id === '2') {
            $scope.title = "本周用水情况";
            $scope.datas = [
                [{
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, ],
                [{
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                },{
                    quantity: " ",
                },{
                    quantity: " ",
                },{
                    quantity: " ",
                }, ]
            ];
        } else {
            $scope.title = "本月用水情况";
            $scope.datas = [
                [{
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, ],
                [{
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, ],
                [{
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, ],
                [{
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, ],
                [{
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, ],
                [{
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: "20升",
                    date: new Date()
                }, ],
                [{
                    quantity: "20升",
                    date: new Date()
                }, {
                    quantity: " ",
                },{
                    quantity: " ",
                },{
                    quantity: " ",
                },{
                    quantity: " ",
                }, ]
            ];
        }
        var getmyServiceById = function (Id, callback) {
		callback(datas);
	};
    	return {
    		getmyServiceById: function(Id, callback) {
			getmyServiceById(Id, callback);
		}
    	};
  });
