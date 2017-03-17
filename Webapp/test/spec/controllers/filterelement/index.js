'use strict';

describe('Controller: FilterelementIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var FilterelementIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    FilterelementIndexCtrl = $controller('FilterelementIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(FilterelementIndexCtrl.awesomeThings.length).toBe(3);
  });
});
