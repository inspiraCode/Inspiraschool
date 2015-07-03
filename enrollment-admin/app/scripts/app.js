'use strict';

/**
 * @ngdoc overview
 * @name enrollmentAdminApp
 * @description
 * # enrollmentAdminApp
 *
 * Main module of the application.
 */
angular
  .module('enrollmentAdminApp', [
    'ngAnimate',
    'ngSanitize',
    'ngTouch',
    'enrollmentAdminApp.home',
    'enrollmentAdminApp.login',
    'enrollmentAdminApp.logout',
    'enrollmentAdminApp.loginService',
    'angular-jwt',
    'angular-storage'
  ])
  .config(function ($urlRouterProvider, jwtInterceptorProvider, $httpProvider) {
    $urlRouterProvider.otherwise('/');

    jwtInterceptorProvider.tokenGetter = function(store){
      return store.get('jwt');
    };

    $httpProvider.interceptors.push('jwtInterceptor');

    // Enable CORS domain calls
    $httpProvider.defaults.useXDomain = true;
  })
  .run(function($rootScope, $state, store, jwtHelper, LoginService){
    $rootScope.loggedUser = LoginService.data;
    $rootScope.$on('$stateChangeStart', function(e, to){
      if(to.data && to.data.requiresLogin){
        var jwt = store.get('jwt');
        if(!jwt || jwtHelper.isTokenExpired(jwt)) {
          e.preventDefault();
          $state.go('login');
        } else {
          var tokenPayload = jwtHelper.decodeToken(jwt);
          LoginService.update(tokenPayload.data.userId, tokenPayload.data.userName);
        }
      }
    });
  })
  .controller('AppCtrl', function AppCtrl( $scope, $location, LoginService ){
    $scope.activePath = null;
    $scope.loggedUser = LoginService.data;
    console.log(LoginService.data);

    $scope.$on('$routeChangeSuccess', function(e, nextRoute){
      $scope.loggedUser = LoginService.data;
      console.log(LoginService.data);
      if( nextRoute.$$route && angular.isDefined( nextRoute.$$route.pageTitle )){
        $scope.pageTitle = nextRoute.$$route.pageTitle + ' | Enrollment Admin';
        $scope.activePath = $location.path();
      }
    });
  });
