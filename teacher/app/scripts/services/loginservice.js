'use strict';

/**
 * @ngdoc service
 * @name teacherApp.LoginService
 * @description
 * # LoginService
 * Factory in the teacherApp.
 */
angular.module('teacherApp').factory('LoginService', function() {
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
