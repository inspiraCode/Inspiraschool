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
  .run(function($rootScope, $state, store, jwtHelper){
    $rootScope.$on('$stateChangeStart', function(e, to){
      if(to.data && to.data.requiresLogin){
        if(!store.get('jwt') || jwtHelper.isTokenExpired(store.get('jwt'))) {
          e.preventDefault();
          $state.go('login');
        }
      }
    });
  })
  .controller('AppCtrl', function AppCtrl( $scope, $location ){
    $scope.activePath = null;
    $scope.$on('$routeChangeSuccess', function(e, nextRoute){
      if( nextRoute.$$route && angular.isDefined( nextRoute.$$route.pageTitle )){
        $scope.pageTitle = nextRoute.$$route.pageTitle + ' | Enrollment Admin';
        $scope.activePath = $location.path();
      }
    });
  });
