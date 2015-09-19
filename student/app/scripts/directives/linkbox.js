'use strict';

/**
 * @ngdoc directive
 * @name studentApp.directive:linkBox
 * @description
 * # linkBox
 */
angular.module('studentApp').directive('linkBox', function() {
    return {
        template: '<a href="" ng-href="{{box.HRef}}"  class="fx-zoom-down fx-speed-500 col-xs-4 col-sm-2 col-md-2 BoxContainer"><span class="LinkBox noselect text-center">{{box.Name}}</span><div class="hidden-xs" style="height:50px;"></div></a>',
        restrict: 'E',
        scope: {
            box: '='
        },
        replace: true,
        link: function postLink(scope) {}
    };
});
