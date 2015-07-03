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
      templateUrl: 'views/home.html',
      data: {
        requiresLogin: true
      }
    });

  })
  .controller('HomeCtrl', function AppCtrl( $rootScope, $scope, $http, LoginService ){
    
    $scope.response = null;
    $scope.enrollmentForms = [];
    $rootScope.loggedUser = LoginService.data;

    $scope.listEnrollmentForms = function(){
      $http({
        url: 'http://localhost:8080/services/enrollments',
        method: 'GET'
      }).then(function(quote){
        $scope.response = quote.data;
      }, function(error){
        $scope.response = error.data;
      });
    };
  });
