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
    'ngTouch',
    'ngResource'
  ]);
app.config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/enrollment', {
        templateUrl: 'views/enrollment.html',
        controller: 'EnrollmentCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });

app.factory('services', function($resource) {
  return $resource('http://localhost:8080/services/create.json');
});