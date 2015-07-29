'use strict';

describe('Service: validatorService', function () {

  // load the service's module
  beforeEach(module('enrollmentApp'));

  // instantiate service
  var validatorService;
  beforeEach(inject(function (_validatorService_) {
    validatorService = _validatorService_;
  }));

  it('should do something', function () {
    expect(!!validatorService).toBe(true);
  });

});
