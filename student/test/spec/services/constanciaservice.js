'use strict';

describe('Service: constanciaService', function () {

  // load the service's module
  beforeEach(module('studentApp'));

  // instantiate service
  var constanciaService;
  beforeEach(inject(function (_constanciaService_) {
    constanciaService = _constanciaService_;
  }));

  it('should do something', function () {
    expect(!!constanciaService).toBe(true);
  });

});
