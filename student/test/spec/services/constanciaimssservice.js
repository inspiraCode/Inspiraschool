'use strict';

describe('Service: constanciaIMSSService', function () {

  // load the service's module
  beforeEach(module('studentApp'));

  // instantiate service
  var constanciaIMSSService;
  beforeEach(inject(function (_constanciaIMSSService_) {
    constanciaIMSSService = _constanciaIMSSService_;
  }));

  it('should do something', function () {
    expect(!!constanciaIMSSService).toBe(true);
  });

});
