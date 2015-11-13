'use strict';

/**
 * @ngdoc service
 * @name teacherApp.appConfig
 * @description
 * # appConfig
 * Constant in the teacherApp.
 */
angular.module('teacherApp').constant('appConfig', {
    //API_URL:'https://centrouniversitariopasodelnorte.com/student_api/' 	//PRODUCTION
    API_URL: 'http://localhost:9090/teacher_api/' //DEVELOPMENT
});
