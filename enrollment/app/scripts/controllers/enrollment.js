'use strict';

/**
 * @ngdoc function
 * @name enrollmentApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the enrollmentApp
 */
angular.module('enrollmentApp').controller('EnrollmentCtrl', function($scope, $location, enrollmentService) {
    //Misc:
    $scope.resetForm = function() {
        $scope.enrollmentForm = enrollmentService.create();
        $scope.enrollmentForm.state = 'Chihuahua';
        $scope.enrollmentForm.city = 'Cd. Juárez';
        $scope.enrollmentForm.folio = 0;
    };

    //Initialize entity:
    $scope.resetForm();

    //Load catalogs:
    $scope.theShifts = enrollmentService.catalogs.Shifts.getAll();
    $scope.theCourses = enrollmentService.catalogs.Courses.getAll();
    $scope.theCoursePlans = enrollmentService.catalogs.CoursePlans.getAll();

    //Server transactions:
    $scope.enrollForm = function() {
        enrollmentService.save($scope.enrollmentForm).then(function(data){
            $scope.enrollmentForm.folio = data.Result.folio;
        });
    };
});
