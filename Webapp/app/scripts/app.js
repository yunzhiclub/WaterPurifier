'use strict';

/**
 * @ngdoc overview
 * @name webappApp
 * @description
 * # webappApp
 *
 * Main module of the application.
 */
angular
  .module('webappApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/teacher', {
        templateUrl: 'views/teacher/index.html',
        controller: 'TeacherIndexCtrl',
        controllerAs: 'teacher/index'
      })
      .when('/login', {
        templateUrl: 'views/login/index.html',
        controller: 'LoginIndexCtrl',
        controllerAs: 'login/index'
      })
      .when('/index', {
        templateUrl: 'views/index/index.html',
        controller: 'IndexIndexCtrl',
        controllerAs: 'index/index'
      })
      .when('/machine', {
        templateUrl: 'views/machine/index.html',
        controller: 'MachineIndexCtrl',
        controllerAs: 'machine/index'
      })
      .when('/onlinedetailers', {
        templateUrl: 'views/onlinedetailers/index.html',
        controller: 'OnlinedetailersIndexCtrl',
        controllerAs: 'onlinedetailers/index'
      })
      .when('/filterelement', {
        templateUrl: 'views/filterelement/index.html',
        controller: 'FilterelementIndexCtrl',
        controllerAs: 'filterelement/index'
      })
      .when('/waterquality', {
        templateUrl: 'views/waterquality/index.html',
        controller: 'WaterqualityIndexCtrl',
        controllerAs: 'waterquality/index'
      })
      .when('/waterconsumption', {
        templateUrl: 'views/waterconsumption/index.html',
        controller: 'WaterconsumptionIndexCtrl',
        controllerAs: 'waterconsumption/index'
      })
      .when('/package', {
        templateUrl: 'views/package/index.html',
        controller: 'PackageIndexCtrl',
        controllerAs: 'package/index'
      })
      .when('/recharge', {
        templateUrl: 'views/recharge/index.html',
        controller: 'RechargeIndexCtrl',
        controllerAs: 'recharge/index'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
