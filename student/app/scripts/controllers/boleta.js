'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:BoletaCtrl
 * @description
 * # BoletaCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('BoletaCtrl', function($scope, $routeParams) {
    $scope.userID = $routeParams.userId;
});
