'use strict';

describe('Controller: ConstanciaconsuEuaCtrl', function () {

  // load the controller's module
  beforeEach(module('studentApp'));

  var ConstanciaconsuEuaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ConstanciaconsuEuaCtrl = $controller('ConstanciaconsuEuaCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ConstanciaconsuEuaCtrl.awesomeThings.length).toBe(3);
  });
});
