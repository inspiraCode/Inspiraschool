'use strict';

/**
 * @ngdoc function
 * @name teacherApp.controller:DocumentsCtrl
 * @description
 * # DocumentsCtrl
 * Controller of the teacherApp
 */
angular.module('teacherApp').controller('DocumentsCtrl', function($scope, $timeout, $activityIndicator, courseService) {
    $activityIndicator.startAnimating();
    alertify.closeAll();

    $scope.documentBoxes = [];

    courseService.loadEntities().then(function(data) {
        var boxes = [];
        var allEntities = courseService.getAll();
        for (var i = 0; i < allEntities.length; i++) {
            var current = allEntities[i];
            boxes.push({
                id: current.id,
                Title: current.grade + '<br>' + current.assignment_name + '<br>' + current.day_trip,
                HRef: '#/notes',
                IMGHref: '',
                View: '',
                Grade: current.grade,
                MetterName: current.assignment_name,
                DayTrip: current.day_trip
            });
        };
        $scope.items = boxes;
        $scope.addBox();
        $activityIndicator.stopAnimating();
    });

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

    $scope.selectCourse = function(course) {
        courseService.setSelected(course);
    };
});
