'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:ConstanciaimssCtrl
 * @description
 * # ConstanciaimssCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('ConstanciaimssCtrl', function($scope, $window, $activityIndicator, constanciaService) {
    $activityIndicator.startAnimating();
    alertify.closeAll();

    $scope.print = function() {
        $window.document.close();
        $window.print();
    };

    $scope.baseEntity = null;
    constanciaService.loadEntities(true).then(function(data) {
        $scope.baseEntity = data[0];
        $scope.baseEntity.period = 'SEPTIEMBRE-DICIEMBRE';
        $activityIndicator.stopAnimating();
    }, function(data) {
        $activityIndicator.stopAnimating();
    });
});
