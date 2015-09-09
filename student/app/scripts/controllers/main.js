'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('MainCtrl', function($scope, $location, $activityIndicator, $timeout) {
    $activityIndicator.stopAnimating();
    alertify.closeAll();

    $scope.activePath = $location.url();
    $scope.go = function(path) {
        if (path != $location.url()) {
            $activityIndicator.startAnimating();
            $timeout(function() {
                $location.url(path);
            }, 100);
        }
    };

    $scope.gotoAnchor = function(x) {
        jQuery('#mainContainer').animate({
            scrollTop: jQuery('#anchor' + x).position().top
        }, 300);
    };

    $scope.logOut = function() {        
        $location.path('/logout');
    };
});
