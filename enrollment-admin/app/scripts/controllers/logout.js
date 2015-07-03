'use strict';

/**
 * @ngdoc overview
 * @name logoutController
 * @description
 * # logoutController
 *
 * Logout page controller.
 */
angular
  .module('enrollmentAdminApp.logout', [
    'ui.router',
    'angular-storage'
  ])
  .config(function ($stateProvider) {
    $stateProvider.state('logout', {
      url: '/logout',
      controller: 'LogoutCtrl',
      templateUrl: 'views/login.html'
    });

  })
  .controller('LogoutCtrl', function AppCtrl( store, $state, LoginService ){
    store.remove('jwt');
    $state.go('home');

    LoginService.update('', '');
  });
