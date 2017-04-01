'use strict';

describe('Controller: OnlinedetailersPasswordCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var OnlinedetailersPasswordCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OnlinedetailersPasswordCtrl = $controller('OnlinedetailersPasswordCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(OnlinedetailersPasswordCtrl.awesomeThings.length).toBe(3);
  });
});
