'use strict';

describe('Service: calificarService', function () {

  // load the service's module
  beforeEach(module('teacherApp'));

  // instantiate service
  var calificarService;
  beforeEach(inject(function (_calificarService_) {
    calificarService = _calificarService_;
  }));

  it('should do something', function () {
    expect(!!calificarService).toBe(true);
  });

});
