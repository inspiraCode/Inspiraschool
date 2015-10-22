'use strict';

/**
 * @ngdoc function
 * @name teacherApp.controller:DocumentsCtrl
 * @description
 * # DocumentsCtrl
 * Controller of the teacherApp
 */
angular.module('teacherApp').controller('DocumentsCtrl', function($scope, documentService, $timeout, $activityIndicator) {
    $activityIndicator.stopAnimating();
    alertify.closeAll();

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

});
