'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:WaterconsumptionDetailCtrl
 * @description
 * # WaterconsumptionDetailCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('WaterconsumptionDetailCtrl', ['$routeParams', '$scope', function($routeParams, $scope) {
        var id = $routeParams.id;
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
    }]);
