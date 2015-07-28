'use strict';

/**
 * @ngdoc overview
 * @name solicitudesController
 * @description
 * # solicitudesController
 * Solicitudes presents a list of enrollment forms to be followed up.
 *
 * Home page controller.
 */
angular
  .module('enrollmentAdminApp.solicitudes', [
    'ui.router'
  ])
  .config(function ($stateProvider) {
    $stateProvider.state('solicitudes', {
      url: '/solicitudes',
      controller: 'SolicitudesCtrl',
      templateUrl: 'views/solicitudes.html',
      data: {
        requiresLogin: true
      }
    });

  })
  .controller('SolicitudesCtrl', function AppCtrl( $scope, $http ){
    
    $scope.response = null;
    $scope.enrollmentForms = [];
    
    $scope.listEnrollmentForms = function(){
      $http({
        url: 'http://localhost:8080/services/enrollments',
        method: 'GET'
      }).then(function(quote){
        $scope.response = quote.data;
      }, function(error){
        $scope.response = error.data;
      });
    };
  });