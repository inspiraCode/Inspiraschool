'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:BoletaCtrl
 * @description
 * # BoletaCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('BoletaCtrl', function($scope, $routeParams, $window, $activityIndicator, boletaService) {
    $activityIndicator.startAnimating();
    alertify.closeAll();

    $scope.print = function() {
        $window.document.close();
        $window.print();
    };

    $scope.baseEntity = null;
    boletaService.loadEntities(true).then(function(data) {
        $scope.baseEntity = data;
        $activityIndicator.stopAnimating();
    }, function(data) {
        $activityIndicator.stopAnimating();
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
