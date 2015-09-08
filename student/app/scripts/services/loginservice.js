'use strict';

/**
 * @ngdoc service
 * @name studentApp.LoginService
 * @description
 * # LoginService
 * Factory in the studentApp.
 */
angular.module('studentApp').factory('LoginService', function() {
    return {
        data: {
            userId: '',
            userName: ''
        },
        update: function(uid, uname) {
            this.data.userId = uid;
            this.data.userName = uname;
        }
    };
});
