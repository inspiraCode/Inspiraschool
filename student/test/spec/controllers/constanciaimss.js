'use strict';

describe('Controller: ConstanciaimssCtrl', function () {

  // load the controller's module
  beforeEach(module('studentApp'));

  var ConstanciaimssCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ConstanciaimssCtrl = $controller('ConstanciaimssCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ConstanciaimssCtrl.awesomeThings.length).toBe(3);
  });
});
