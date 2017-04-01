'use strict';

describe('Controller: WaterconsumptionDetailCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var WaterconsumptionDetailCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    WaterconsumptionDetailCtrl = $controller('WaterconsumptionDetailCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(WaterconsumptionDetailCtrl.awesomeThings.length).toBe(3);
  });
});
