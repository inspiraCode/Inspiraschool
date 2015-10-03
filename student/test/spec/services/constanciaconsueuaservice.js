'use strict';

describe('Service: constanciaConsuEUAService', function () {

  // load the service's module
  beforeEach(module('studentApp'));

  // instantiate service
  var constanciaConsuEUAService;
  beforeEach(inject(function (_constanciaConsuEUAService_) {
    constanciaConsuEUAService = _constanciaConsuEUAService_;
  }));

  it('should do something', function () {
    expect(!!constanciaConsuEUAService).toBe(true);
  });

});
