'use strict';

angular.module('inspiraschoolApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


