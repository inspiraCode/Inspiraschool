'use strict';

/**
 * @ngdoc directive
 * @name enrollmentAdminApp.directive:accordion
 * @description
 * # accordion
 */
angular.module('enrollmentAdminApp').directive('accordion', function() {
    return {
        restrict: 'EA',
        replace: true,
        transclude: true,
        template: '<div ng-transclude></div>',
        scope: true,
        controller: function($scope) {
            var convertToNumberingScheme = function(number) {
                var baseChar = ("a").charCodeAt(0),
                    letters = "";
                do {
                    number -= 1;
                    letters = String.fromCharCode(baseChar + (number % 26)) + letters;
                    number = (number / 26) >> 0;
                } while (number > 0);

                return letters;
            };

            this.getBullet = function(parentScope, currentScope, level, index) {
                if (parentScope.bulletPrefix == undefined) {
                    parentScope.bulletPrefix = '';
                }

                if (level % 2 != 0) {
                    currentScope.bulletPrefix = '' + parentScope.bulletPrefix + (index + 1) + '.';
                    return currentScope.bulletPrefix;
                } else {
                    currentScope.bulletPrefix = '' + parentScope.bulletPrefix + convertToNumberingScheme(index + 1) + '.';
                    return currentScope.bulletPrefix;
                }
            };

            var expanders = [];
            this.gotOpened = function(selectedExpander) {
                angular.forEach(expanders, function(expander) {
                    if (selectedExpander != expander) {
                        expander.showMe = false;
                    }
                });
            }
            this.addExpander = function(expander) {
                expanders.push(expander);
            }
        }
    }
});
