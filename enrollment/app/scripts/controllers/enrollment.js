'use strict';

/**
 * @ngdoc function
 * @name enrollmentApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the enrollmentApp
 */
angular.module('enrollmentApp')
  .controller('EnrollmentCtrl', function ($scope) {
  	$scope.enrollmentForm = {state:'Chihuahua', city:'Cd. Juárez'};
  });
