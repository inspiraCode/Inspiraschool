'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:BoletaCtrl
 * @description
 * # BoletaCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('BoletaCtrl', function($scope, $routeParams, $window, $activityIndicator, boletaService) {
    $activityIndicator.stopAnimating();
    alertify.closeAll();

    $scope.print = function() {
        $window.document.close();
        $window.print();
    };

    $scope.baseEntity = [];
    boletaService.loadEntities(true).then(function(data) {
        $scope.baseEntity = data;
    });
    $scope.getAverage = function() {
        var matters = $scope.baseEntity;
        var sumPartialsOne = 0;
        var mattersCount = matters.length;
        for (var i = 0; i < matters.length; i++) {
            var current = matters[i];
            sumPartialsOne += Number(current.partial_one);
        };
        return sumPartialsOne / mattersCount;
    };
});
