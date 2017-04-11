'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:OnlinedetailersIndexCtrl
 * @description
 * # OnlinedetailersIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('OnlinedetailersIndexCtrl', function ($scope) {
    	$scope.value = undefined;
        $scope.items = [
            { id: 1, name: 'item1', img: '../images/1.jpg' },
            { id: 2, name: 'item2', img: '../images/2.jpg' },
            { id: 3, name: 'item3', img: '../images/3.jpg' }
        ];
  });
