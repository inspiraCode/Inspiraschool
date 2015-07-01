'use strict';

/**
 * @ngdoc function
 * @name enrollmentApp.controller:MenuClassCtrl
 * @description
 * # MenuClassCtrl
 * Controller of the enrollmentApp
 */
angular.module('enrollmentApp')
  .controller('MenuClassCtrl', function ($scope, $location) {
    $scope.activePath = null;
  	$scope.$on('$routeChangeSuccess', function(){
    	$scope.activePath = $location.path();
  	});
  });