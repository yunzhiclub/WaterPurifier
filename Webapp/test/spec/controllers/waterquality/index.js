'use strict';

describe('Controller: WaterqualityIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var WaterqualityIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    WaterqualityIndexCtrl = $controller('WaterqualityIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(WaterqualityIndexCtrl.awesomeThings.length).toBe(3);
  });
});
