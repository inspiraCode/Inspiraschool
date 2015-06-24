'use strict';

/**
 * @ngdoc function
 * @name enrollmentApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the enrollmentApp
 */
angular.module('enrollmentApp')
  .controller('EnrollmentCtrl', function ($scope, $location, $http) {
  	$scope.enrollmentForm = {state:'Chihuahua', city:'Cd. Juárez', folio:0};
    $scope.shifts = [
      {'shift_code':'d', 'shift_name':'Dominical'}, 
      {'shift_code':'e', 'shift_name':'Empresarial'}, 
      {'shift_code':'s', 'shift_name':'Sabatino'}, 
      {'shift_code':'v', 'shift_name':'Virtual'}];

    $scope.courses = [
      {'course_code':'pp', 'course_name':'Preparatoria', 'shift_code':'d'}, 
      {'course_code':'un', 'course_name':'Universidad', 'shift_code':'d'},
      {'course_code':'dp', 'course_name':'Diplomado', 'shift_code':'d'},
      {'course_code':'pp', 'course_name':'Preparatoria', 'shift_code':'e'}, 
      {'course_code':'un', 'course_name':'Universidad', 'shift_code':'e'},
      {'course_code':'dp', 'course_name':'Diplomado', 'shift_code':'e'},
      {'course_code':'pp', 'course_name':'Preparatoria', 'shift_code':'s'}, 
      {'course_code':'un', 'course_name':'Universidad', 'shift_code':'s'},
      {'course_code':'dp', 'course_name':'Diplomado', 'shift_code':'s'},
      {'course_code':'pp', 'course_name':'Preparatoria', 'shift_code':'v'}, 
      {'course_code':'un', 'course_name':'Universidad', 'shift_code':'v'},
      {'course_code':'dp', 'course_name':'Diplomado', 'shift_code':'v'}
      ];

    $scope.coursePlans = [
      {'plan_code':'LEAE', 'plan_name':'Lic. En Administración de Empresas', 'course_code':'un'},
      {'plan_code':'LECP', 'plan_name':'Lic. En Contaduría Pública', 'course_code':'un'},
      {'plan_code':'LEDP', 'plan_name':'Lic. En Derecho', 'course_code':'un'},
      {'plan_code':'BGPS', 'plan_name':'Bachillerato General Plan Semestral', 'course_code':'pp'},
      {'plan_code':'BGPT', 'plan_name':'Bachillerato General Plan Tetramestral', 'course_code':'pp'},
      {'plan_code':'DPAE', 'plan_name':'Dimplomado en Asistencia Educativa', 'course_code':'dp'},
      {'plan_code':'DAFS', 'plan_name':'Diplomado en Asistencia Familiar y de Salud', 'course_code':'dp'}
      ];

  	$scope.resetForm = function() {
  		$scope.enrollmentForm = {state:'Chihuahua', city:'Cd. Juárez'};
  	};
  	$scope.enrollForm = function() {
  		//console.log($scope.enrollmentForm);
      var req = {
        method:'POST',
        url: 'http://localhost:8080/services/subscribe',
        headers: {
          // REMOVE CONTENT TYPE DUE TO CORS Acceptance.
          'Content-Type': undefined
        },
        data: $scope.enrollmentForm
      };
      
      $http(req).
        success(function(data){
          $scope.enrollmentForm = data.data;
        }).
        error(function(data, status){
          console.log('error');
          console.log(data);
          console.log(status);
        });
  	};
  });
