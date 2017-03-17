'use strict';

describe('Controller: MachineIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MachineIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MachineIndexCtrl = $controller('MachineIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MachineIndexCtrl.awesomeThings.length).toBe(3);
  });
});
