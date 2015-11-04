'use strict';

/**
 * @ngdoc directive
 * @name teacherApp.directive:linkBox
 * @description
 * # linkBox
 */
angular.module('teacherApp').directive('linkBox', function() {
    return {
        template: '<a href="" ng-href="{{box.HRef}}" class="fx-zoom-down fx-speed-500 col-xs-12 col-sm-4 col-md-4 BoxContainer"><span class="LinkBox noselect text-center" ng-bind-html="box.Title"></span><div class="hidden-xs" style="height:20px;"></div></a>',
        restrict: 'E',
        scope: {
            box: '='
        },
        replace: true,
        link: function postLink(scope) {}
    };
});
