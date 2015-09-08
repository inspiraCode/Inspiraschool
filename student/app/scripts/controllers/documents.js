'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:DocumentsCtrl
 * @description
 * # DocumentsCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('DocumentsCtrl', function($scope, documentService, boletaService) {
    // boletaService.loadAll().then(function(data) {
        $scope.documentBoxes = documentService.getAll();
    // });
});
