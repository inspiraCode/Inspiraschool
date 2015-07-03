'use strict';

/**
 * @ngdoc overview
 * @name homeController
 * @description
 * # homeController
 *
 * Home page controller.
 */
angular
  .module('enrollmentAdminApp.home', [
    'ui.router',
    'angular-jwt',
    'angular-storage',
    'angular.morris-chart'    
  ])
  .config(function ($stateProvider) {
    $stateProvider.state('home', {
      url: '/',
      controller: 'HomeCtrl',
      templateUrl: 'views/home.html',
      data: {
        requiresLogin: true
      }
    });

  })
  .controller('HomeCtrl', function AppCtrl( $rootScope, $scope, $http, LoginService ){
    
    $scope.response = null;
    $scope.enrollmentForms = [];
    $rootScope.loggedUser = LoginService.data;

    $scope.chartExample = {area : [
                      { y: '2005', a: 25, b: 90 },
                      { y: '2006', a: 100, b: 90 },
                      { y: '2007', a: 75,  b: 65 },
                      { y: '2008', a: 50,  b: 40 },
                      { y: '2009', a: 75,  b: 65 },
                      { y: '2010', a: 50,  b: 40 },
                      { y: '2011', a: 75,  b: 65 },
                      { y: '2012', a: 100, b: 90 }
                    ],
                    bar: [
                        { y: "2006", a: 100, b: 90 },
                        { y: "2007", a: 75,  b: 65 },
                        { y: "2008", a: 50,  b: 40 },
                        { y: "2009", a: 75,  b: 65 },
                        { y: "2010", a: 50,  b: 40 },
                        { y: "2011", a: 75,  b: 65 },
                        { y: "2012", a: 100, b: 90 }
                    ]
                  };

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