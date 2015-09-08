'use strict';

describe('Controller: BoletaCtrl', function () {

  // load the controller's module
  beforeEach(module('studentApp'));

  var BoletaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BoletaCtrl = $controller('BoletaCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(BoletaCtrl.awesomeThings.length).toBe(3);
  });
});
