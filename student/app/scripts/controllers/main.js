'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('MainCtrl', function($scope, $location, $activityIndicator, $timeout, LoginService, store) {
    $activityIndicator.stopAnimating();
    alertify.closeAll();
    

    // $scope.activePath = $location.url();
    $scope.go = function(path) {
        if (path != $location.url()) {
            $timeout(function() {
                $location.url(path);
            }, 100);
        }
    };

    $scope.hideMenuOpened = function() {
        if (angular.element(".navbar-toggle").is(':visible')) {
            angular.element(".navbar-toggle").click();
        }
    };

    $scope.gotoAnchor = function(x) {
        jQuery('#mainContainer').animate({
            scrollTop: jQuery('#anchor' + x).position().top
        }, 300);
    };

    $scope.logOut = function() {
        LoginService.update('', '');
        store.remove('jwt');
        $scope.go('/login');
    };

    $timeout(function(){
        angular.element('.DelayElement').show();
    }, 500);
});
