'use strict';

/**
 * @ngdoc overview
 * @name loginService
 * @description
 * # loginService
 *
 * Shares information about the logged user.
 */
angular
  .module('enrollmentAdminApp.loginService', [])
  .factory('LoginService', function () {
    return {
    	data: {userId:'',
    			userName:''
    	},
    	update: function(uid, uname){
    		this.data.userId = uid;
    		this.data.userName = uname;
    	}
    };
  });