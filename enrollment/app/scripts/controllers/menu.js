'use strict';

/**
 * @ngdoc function
 * @name enrollmentApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the enrollmentApp
 */
angular.module('enrollmentApp')
  .controller('MenuClassCtrl', function ($scope, $location) {
    $scope.activePath = null;
  	$scope.$on('$routeChangeSuccess', function(){
    	$scope.activePath = $location.path();
    	//console.log( $location.path() );
  	});
  });