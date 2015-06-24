'use strict';

/**
 * @ngdoc overview
 * @name enrollmentApp
 * @description
 * # enrollmentApp
 *
 * Main module of the application.
 */
 var app = angular.module('enrollmentApp', [
    'ngAnimate',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ]);
app.config(function ($routeProvider, $httpProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/enrollment', {
        templateUrl: 'views/enrollment.html',
        controller: 'EnrollmentCtrl'
      })
      .when('/result', {
        templateUrl: 'views/result.html',
        controller: 'ResultCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
      // Enable cross domain calls
      $httpProvider.defaults.useXDomain = true;

      // Remove the header containing XMLHttpRequest used to identify ajax calls
      // that would prevent CORS from working
      delete $httpProvider.defaults.headers.common['X-Requested-With'];
  });