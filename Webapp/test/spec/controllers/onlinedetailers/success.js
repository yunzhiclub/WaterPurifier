'use strict';

describe('Controller: OnlinedetailersSuccessCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var OnlinedetailersSuccessCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OnlinedetailersSuccessCtrl = $controller('OnlinedetailersSuccessCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(OnlinedetailersSuccessCtrl.awesomeThings.length).toBe(3);
  });
});
