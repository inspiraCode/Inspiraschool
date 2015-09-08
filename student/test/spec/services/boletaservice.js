'use strict';

describe('Service: boletaService', function () {

  // load the service's module
  beforeEach(module('studentApp'));

  // instantiate service
  var boletaService;
  beforeEach(inject(function (_boletaService_) {
    boletaService = _boletaService_;
  }));

  it('should do something', function () {
    expect(!!boletaService).toBe(true);
  });

});
