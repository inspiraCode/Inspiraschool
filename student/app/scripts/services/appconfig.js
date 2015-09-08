'use strict';

/**
 * @ngdoc service
 * @name studentApp.appConfig
 * @description
 * # appConfig
 * Constant in the studentApp.
 */
angular.module('studentApp').constant('appConfig', {
    //API_URL:'http://apps.capsonic.com/IQS_Backend/api/' 	//PRODUCTION
    API_URL: 'http://localhost:9090/services/' //DEVELOPMENT
});
