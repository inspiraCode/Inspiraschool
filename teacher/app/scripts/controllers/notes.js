'use strict';

/**
 * @ngdoc function
 * @name teacherApp.controller:NotesCtrl
 * @description
 * # NotesCtrl
 * Controller of the teacherApp
 */
angular.module('teacherApp').controller('NotesCtrl', function($scope, noteService, $window, courseService, $activityIndicator, $q, calificarService) {
    $activityIndicator.startAnimating();
    alertify.closeAll();

    $scope.courseSelected = courseService.selectedEntity;
    if ($scope.courseSelected) {
        noteService.loadEntities(true, '?course_id=' + $scope.courseSelected.id).then(function(data) {
            $scope.theNotes = data.data.Result;
            $activityIndicator.stopAnimating();
        });
    } else {
        $scope.go('documents');
    }

    $scope.print = function() {
        $window.document.close();
        $window.print();
    };

    $scope.saveSingle = function(theNote) {
        return calificarService.formPost(theNote).then(function(data) {
            theNote.done = true;
        });
    };

    $scope.saveAll = function() {
        $activityIndicator.startAnimating();
        var arrNotes = $scope.theNotes;

        var arrPromiseConstructors = [];
        angular.forEach(arrNotes, function(theNote, key) {
            theNote.id_group_assignment = $scope.courseSelected.id;
            var promiseConstructor = function() {
                return $scope.saveSingle(theNote);
            }
            arrPromiseConstructors.push(promiseConstructor);
        });

        $q.serial(arrPromiseConstructors).finally(function(data) {
            $activityIndicator.stopAnimating();
            $timeout(function() {
                alertify.message('Process completed.');
            }, 100);
        });

        //Parallel way:************************************************
        // var arrPromiseConstructors = [];
        // angular.forEach(arrNotes, function(theNote, key) {
        //     theNote.id_group_assignment = $scope.courseSelected.id;
        //     arrPromiseConstructors.push($scope.saveSingle(theNote));
        // });

        // $q.all(arrPromiseConstructors).finally(function(data) {
        //     $activityIndicator.stopAnimating();
        //     $timeout(function() {
        //         alertify.message('Process completed.');
        //     }, 100);
        // });
        //*************************************************************
    };
});
