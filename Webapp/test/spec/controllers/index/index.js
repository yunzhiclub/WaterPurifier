'use strict';

describe('Controller: IndexIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var IndexIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    IndexIndexCtrl = $controller('IndexIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(IndexIndexCtrl.awesomeThings.length).toBe(3);
  });
});
