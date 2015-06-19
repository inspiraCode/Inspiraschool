'use strict';

/**
 * @ngdoc function
 * @name enrollmentApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the enrollmentApp
 */
angular.module('enrollmentApp')
  .controller('EnrollmentCtrl', function ($scope, $location) {
  	//$scope.enrollmentForm = {state:'Chihuahua', city:'Cd. Juárez'};
    $scope.enrollmentForm = {'state':'Chihuahua','city':'Cd. Juárez','shift':'v','course':'dp','coursePlan':'DPAE','firstName':'Diego Alejandro','lastName':'Torres Fuerte','gender':'m','birthPlace':'Cd. Juárez, Chihuahua','birthDate':'04/13/1982','personId':'TOFD820413HCHRRG09','address1':'Calle Valle Grande Sur 1528','address2':'Parajes del Valle','zip':'32590','email':'diego.torres.fuerte@gmail.com','fromSchool':'SECUNDARIA FEDERAL 8','phone':'324-2033','cellPhone':'642-3887'};
  	$scope.resetForm = function() {
  		$scope.enrollmentForm = {state:'Chihuahua', city:'Cd. Juárez'};
  	};
  	$scope.enrollForm = function() {
  		console.log($scope.enrollmentForm);
      //console.log(services.insertEnrollment($scope.enrollmentForm));
  		$location.path('/');
  	};
  });
