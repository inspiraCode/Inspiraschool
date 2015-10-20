'use strict';

/**
 * @ngdoc function
 * @name teacherApp.controller:NotesCtrl
 * @description
 * # NotesCtrl
 * Controller of the teacherApp
 */
angular.module('teacherApp').controller('NotesCtrl', function($scope, noteService, $window) {
    // noteService.readByParentId().then(function(data) {

    // });
    $scope.print = function() {
        $window.document.close();
        $window.print();
    };
    $scope.theNotes = noteService.getAll();
});
