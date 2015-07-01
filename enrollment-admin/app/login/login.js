'use strict';

/**
 * @ngdoc overview
 * @name loginController
 * @description
 * # loginController
 *
 * Login page controller.
 */
angular
  .module('enrollmentAdminApp.login', [
    'ui.router',
    'angular-storage'
  ])
  .config(function ($stateProvider) {
    $stateProvider.state('login', {
      url: '/login',
      controller: 'LoginCtrl',
      templateUrl: 'login/login.html'
    });

  })
  .controller('LoginCtrl', function AppCtrl( $scope, $http, store, $state ){
    
    $scope.user = {};
    
    $scope.login = function(){
      $http({
        url: 'http://localhost:8080/services/login',
        method: 'GET',
        data: $scope.user
      }).then(function(quote){
        store.set('jwt', response.data.id_token);
        $state.go('home');
      }, function(error){
        alert(error.data);
      });
    }
  });
