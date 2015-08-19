'use strict';

/**
 * @ngdoc directive
 * @name enrollmentApp.directive:dateDirective
 * @description
 * # dateDirective
 */
angular.module('enrollmentApp').directive('dateDirective', function() {
    return {
        restrict: 'E',
        transclude: true,
        replace: true,
        scope: {
            theDate: '='
        },
        template: '<div class="input-group" ng-transclude ng-class="{\'has-success\':isValidDate(theDate) == \'\', \'has-error\':!isValidDate(theDate) == \'\'}"></div>',
        compile: function compile(tElement, tAttrs, transclude) {
            return function postLink(scope, iElement, iAttrs) {
                scope.isValidDate = function(sDate) {
                    var result = '';
                    var theDate = moment(sDate, 'MM/DD/YYYY');
                    if (theDate.isValid() == false) {
                        result = 'Invalid Date';
                    }
                    var minDate = moment('02/10/1985', 'MM/DD/YYYY');
                    var maxDate = moment('02/10/2200', 'MM/DD/YYYY');

                    if (theDate.isBefore(minDate)) {
                        result = 'Date too old.'
                    }
                    if (theDate.isAfter(maxDate)) {
                        result = 'Date not allowed.'
                    }
                    return result;
                };
                iElement.append('<span class="input-group-btn" style="outline:none; z-index: 2;" tabindex="-1"><input type="text" style="visibility:hidden;"></span>');
                scope.datePickerField = jQuery(iElement).find('span').find('input');
                scope.datepicker = jQuery(scope.datePickerField).Zebra_DatePicker({
                    format: 'm/d/Y',
                    onSelect: function(a, b, c, d) {
                        var selectedDate = a;
                        scope.$apply(function() {
                            scope.theDate = selectedDate;
                        });
                    },
                    onClear: function() {
                        scope.$apply(function() {
                            scope.theDate = '';
                        });
                    },
                    start_date: scope.theDate
                }).data('Zebra_DatePicker');
                scope.datePickerField.hide();

                scope.$watch(function() {
                    return scope.theDate;
                }, function(newValue, oldValue) {
                    var sValidDate = scope.isValidDate(newValue);
                    if (sValidDate == '') {
                        scope.datePickerField.val(newValue);
                    }
                });

            }
        },
    };
});
