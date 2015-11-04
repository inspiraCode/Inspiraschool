'use strict';

/**
 * @ngdoc function
 * @name teacherApp.controller:NotesCtrl
 * @description
 * # NotesCtrl
 * Controller of the teacherApp
 */
angular.module('teacherApp').controller('NotesCtrl', function($scope, noteService, $window, courseService, $activityIndicator) {
    $activityIndicator.startAnimating();
    alertify.closeAll();

    $scope.courseSelected = courseService.selectedEntity;
    if ($scope.courseSelected) {
        noteService.pullNotesPerCourse($scope.courseSelected.id).then(function(data) {
            $scope.theNotes = data;
            $activityIndicator.stopAnimating();
        });
    } else {
        $scope.go('documents');
    }

    $scope.print = function() {
        $window.document.close();
        $window.print();
    };
});
