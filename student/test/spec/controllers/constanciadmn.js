'use strict';

describe('Controller: ConstanciadmnCtrl', function () {

  // load the controller's module
  beforeEach(module('studentApp'));

  var ConstanciadmnCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ConstanciadmnCtrl = $controller('ConstanciadmnCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ConstanciadmnCtrl.awesomeThings.length).toBe(3);
  });
});
