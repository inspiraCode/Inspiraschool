'use strict';

/**
 * @ngdoc function
 * @name enrollmentApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the enrollmentApp
 */
angular.module('enrollmentApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
