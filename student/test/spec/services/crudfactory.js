'use strict';

describe('Service: crudFactory', function () {

  // load the service's module
  beforeEach(module('studentApp'));

  // instantiate service
  var crudFactory;
  beforeEach(inject(function (_crudFactory_) {
    crudFactory = _crudFactory_;
  }));

  it('should do something', function () {
    expect(!!crudFactory).toBe(true);
  });

});
