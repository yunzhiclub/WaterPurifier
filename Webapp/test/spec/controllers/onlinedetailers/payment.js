'use strict';

describe('Controller: OnlinedetailersPaymentCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var OnlinedetailersPaymentCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OnlinedetailersPaymentCtrl = $controller('OnlinedetailersPaymentCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(OnlinedetailersPaymentCtrl.awesomeThings.length).toBe(3);
  });
});
