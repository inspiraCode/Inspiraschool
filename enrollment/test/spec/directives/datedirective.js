'use strict';

describe('Directive: dateDirective', function () {

  // load the directive's module
  beforeEach(module('enrollmentApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<date-directive></date-directive>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the dateDirective directive');
  }));
});
