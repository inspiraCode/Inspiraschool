'use strict';

/**
 * @ngdoc overview
 * @name homeController
 * @description
 * # homeController
 *
 * Home page controller.
 */
angular
  .module('enrollmentAdminApp.home', [
    'ui.router',
    'angular-jwt',
    'angular-storage'
  ])
  .config(function ($stateProvider) {
    $stateProvider.state('home', {
      url: '/',
      controller: 'HomeCtrl',
      templateUrl: 'home/home.html',
      data: {
        requiresLogin: true
      }
    });

  })
  .controller('HomeCtrl', function AppCtrl( $scope, $http, store, jwtHelper ){
    
    $scope.response = null;
    $scope.enrollmentForms = [];

    $scope.listEnrollmentForms = function(){
      $http({
        url: 'http://localhost:8080/services/enrollments',
        method: 'GET'
      }).then(function(quote){
        $scope.response = quote.data;
      }, function(error){
        $scope.response = error.data;
      });
    }
  });