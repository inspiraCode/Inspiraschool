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
      templateUrl: 'views/login.html'
    });

  })
  .controller('LoginCtrl', function AppCtrl( $scope, $http, store, $state, LoginService, jwtHelper ){
    $scope.loginData = LoginService.data;
    $scope.login = function(){

      var req = {
        method:'POST',
        url: 'http://localhost:9090/services/login',
        headers: {
          'Content-Type': 'application/json'
        },
        data: $scope.user,
        dataType: 'json'
      };
      
      $http(req).
        success(function(quote){
          if(quote.jwt){
            store.set('jwt', quote.jwt);
            $state.go('home');
            var tokenPayload = jwtHelper.decodeToken(quote.jwt);
            LoginService.update(tokenPayload.data.userId, tokenPayload.data.userName);
          } else {
            $scope.error = 'Usuario incorrecto';
          }
        }).
        error(function(){
          $scope.error = 'Usuario o contraseña incorrectos';
        });
    };
  });
