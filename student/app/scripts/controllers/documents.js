'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:DocumentsCtrl
 * @description
 * # DocumentsCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('DocumentsCtrl', function($scope, documentService, boletaService, $timeout) {
    // boletaService.loadEntity().then(function(data) {

        $scope.documentBoxes = [];
        $scope.items = documentService.getAll();

        //Animation:
        $scope.addBox = function(index) {
            if (index === undefined) {
                index = 0;
            }
            if ($scope.items[index]) {
                $timeout(function() {
                    $scope.documentBoxes.push($scope.items[index]);
                    $scope.addBox(++index);
                }, 100);
            } else {
                return;
            }
        };
        $scope.addBox();
    // });
});
