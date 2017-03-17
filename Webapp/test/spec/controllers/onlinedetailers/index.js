'use strict';

describe('Controller: OnlinedetailersIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var OnlinedetailersIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    OnlinedetailersIndexCtrl = $controller('OnlinedetailersIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(OnlinedetailersIndexCtrl.awesomeThings.length).toBe(3);
  });
});
