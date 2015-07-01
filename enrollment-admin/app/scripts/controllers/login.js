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
  .controller('LoginCtrl', function AppCtrl( $scope, $http, store, $state ){
    
    $scope.user = {};
    
    $scope.login = function(){

      var req = {
        method:'POST',
        url: 'http://localhost:8080/services/login',
        headers: {
          // REMOVE CONTENT TYPE DUE TO CORS Acceptance.
          'Content-Type': undefined
        },
        data: $scope.user
      };
      
      $http(req).
        success(function(quote){
          if(quote.jwt){
            store.set('jwt', quote.jwt);
            $state.go('home');
          } else {
            $scope.error = "No fue posible autenticar al usuario";
          }
        }).
        error(function(){
          console.log('Usuaro o contraseña incorrectos');
          $scope.error = "Usuario o contraseña incorrectos";
        });
    };
  });
