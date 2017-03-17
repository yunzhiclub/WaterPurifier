'use strict';

describe('Controller: LoginIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var LoginIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    LoginIndexCtrl = $controller('LoginIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(LoginIndexCtrl.awesomeThings.length).toBe(3);
  });
});
