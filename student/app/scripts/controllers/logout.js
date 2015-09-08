'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:LogoutCtrl
 * @description
 * # LogoutCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('LogoutCtrl', function($scope, store, $state, LoginService) {
    store.remove('jwt');
    $scope.go('/login');

    LoginService.update('', '');
});
