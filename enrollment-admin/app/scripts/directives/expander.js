'use strict';

/**
 * @ngdoc directive
 * @name enrollmentAdminApp.directive:expander
 * @description
 * # expander
 */
angular.module('enrollmentAdminApp').directive('expanderHeader', function() {
    return {
        restrict: 'EA',
        replace: true,
        transclude: true,
        require: '^?accordion',
        template: '<div class="panel-heading"  ng-click="toggle();$event.stopPropagation();" ng-transclude>',
        link: function(scope, element, attrs, accordionController) {
            scope.showMe = false;
            scope.expanderHeaderLevel = scope.$parent.expanderHeaderLevel ? scope.$parent.expanderHeaderLevel + 1 : 1;
            scope.bullet = accordionController.getBullet(scope.$parent, scope, scope.expanderHeaderLevel, scope.$index);
            accordionController.addExpander(scope);

            scope.toggle = function() {
                scope.showMe = !scope.showMe;
                accordionController.gotOpened(scope);
            }
        }
    }
});

angular.module('enrollmentAdminApp').directive('expanderContent', function() {
    return {
        restrict: 'EA',
        replace: true,
        transclude: true,
        template: '<div class="panel-body" ng-click="focusMe();$event.stopPropagation();" style="padding-right: 1px; margin-right:1px;" ng-show="showMe" ng-transclude></div>',
        link: function(scope, element) {
            scope.$watch(function() {
                return scope.showMe;
            }, function() {
                if (scope.showMe) {
                    angular.element('.borderBlue').removeClass('borderBlue');
                    element.addClass('borderBlue');
                } else {
                    if (scope.expanderHeaderLevel > 1) {
                        angular.element('.borderBlue').removeClass('borderBlue');
                        element.parent().parent().parent().parent().addClass('borderBlue');
                    }
                }
            });
            scope.focusMe = function() {
                angular.element('.borderBlue').removeClass('borderBlue');
                element.addClass('borderBlue');
            }
        }
    }
});
