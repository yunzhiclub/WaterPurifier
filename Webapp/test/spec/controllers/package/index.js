'use strict';

describe('Controller: PackageIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var PackageIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PackageIndexCtrl = $controller('PackageIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(PackageIndexCtrl.awesomeThings.length).toBe(3);
  });
});
