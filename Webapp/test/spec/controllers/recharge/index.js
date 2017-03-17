'use strict';

describe('Controller: RechargeIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RechargeIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RechargeIndexCtrl = $controller('RechargeIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(RechargeIndexCtrl.awesomeThings.length).toBe(3);
  });
});
