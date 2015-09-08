'use strict';

/**
 * @ngdoc service
 * @name enrollmentApp.appConfig
 * @description
 * # appConfig
 * Constant in the enrollmentApp.
 */
angular.module('enrollmentApp').constant('appConfig', {
    //API_URL:'http://apps.capsonic.com/IQS_Backend/api/' 	//PRODUCTION
    API_URL: 'http://localhost:8080/services/' //DEVELOPMENT
});
