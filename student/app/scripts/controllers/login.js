'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('LoginCtrl', function($scope, appConfig, $http, store, LoginService, jwtHelper, $activityIndicator) {
    $scope.loginData = LoginService.data;
    $scope.login = function() {

        var req = {
            method: 'POST',
            url: appConfig.API_URL + '/login',
            headers: {
                'Content-Type': 'application/json'
            },
            data: $scope.user,
            dataType: 'json'
        };

        $http(req).
        success(function(quote) {
            if (quote.jwt) {
                store.set('jwt', quote.jwt);
                $scope.go('/');
                var tokenPayload = jwtHelper.decodeToken(quote.jwt);
                LoginService.update(tokenPayload.data.userId, tokenPayload.data.userName);
            } else {
                $scope.error = 'Usuario incorrecto';
            }
        }).
        error(function() {
            $scope.error = 'Usuario o contraseña incorrectos';
        });
    };
});
