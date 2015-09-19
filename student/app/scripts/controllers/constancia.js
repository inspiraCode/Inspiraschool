'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:ConstanciaCtrl
 * @description
 * # ConstanciaCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('ConstanciaCtrl', function($scope, $routeParams, $window, $activityIndicator, constanciaService) {
    $activityIndicator.startAnimating();
    alertify.closeAll();

    $scope.print = function() {
        $window.document.close();
        $window.print();
    };

    $scope.baseEntity = null;
    constanciaService.loadEntity(true).then(function(data) {
        $scope.baseEntity = data;
        $activityIndicator.stopAnimating();
    }, function(data) {
        $activityIndicator.stopAnimating();
    });
});
