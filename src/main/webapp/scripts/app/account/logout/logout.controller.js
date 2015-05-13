'use strict';

angular.module('inspiraschoolApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
