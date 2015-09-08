'use strict';

describe('Controller: ConstanciaCtrl', function () {

  // load the controller's module
  beforeEach(module('studentApp'));

  var ConstanciaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ConstanciaCtrl = $controller('ConstanciaCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ConstanciaCtrl.awesomeThings.length).toBe(3);
  });
});
