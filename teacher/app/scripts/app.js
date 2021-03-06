'use strict';

/**
 * @ngdoc overview
 * @name teacherApp
 * @description
 * # teacherApp
 *
 * Main module of the application.
 */
angular.module('teacherApp', [
    'ngAnimate',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'angular-jwt',
    'angular-storage',
    'ngActivityIndicator',
    'ngFx',
    'inspiracode.crudFactory'
]).config(function($routeProvider, $activityIndicatorProvider, jwtInterceptorProvider, $httpProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'views/main.html',
            controller: 'MainCtrl',
            controllerAs: 'main'
        })
        .when('/about', {
            templateUrl: 'views/about.html',
            controller: 'AboutCtrl',
            controllerAs: 'about'
        })
        .when('/documents', {
            templateUrl: 'views/documents.html',
            controller: 'DocumentsCtrl',
            controllerAs: 'documents'
        })
        .when('/login', {
            templateUrl: 'views/login.html',
            controller: 'LoginCtrl',
            controllerAs: 'login'
        })
        .when('/notes', {
            templateUrl: 'views/notes.html',
            controller: 'NotesCtrl',
            controllerAs: 'notes'
        })
        .otherwise({
            redirectTo: '/'
        });
    $activityIndicatorProvider.setActivityIndicatorStyle('CircledWhite');
    alertify.set('notifier', 'position', 'bottom-left');
    alertify.defaults.glossary.title = 'Estudiante';
    jwtInterceptorProvider.tokenGetter = function(store) {
        return store.get('jwt');
    };
    $httpProvider.interceptors.push('jwtInterceptor');
    // Enable CORS domain calls
    $httpProvider.defaults.useXDomain = true;
    // Remove the header containing XMLHttpRequest used to identify ajax calls
    // that would prevent CORS from working
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
}).run(function($rootScope, store, jwtHelper, LoginService, $location) {
    $rootScope.loggedUser = LoginService.data;

    // register listener to watch route changes
    $rootScope.$on("$routeChangeStart", function(event, next, current) {
        var jwt = store.get('jwt');
        if (!jwt || jwtHelper.isTokenExpired(jwt)) {
            // no logged user, we should be going to #login
            if (next.templateUrl == "/login.html") {
                // already going to #login, no redirect needed
            } else {
                $location.path('/login');
            }
        } else {
            var tokenPayload = jwtHelper.decodeToken(jwt);
            LoginService.update(tokenPayload.data.userId, tokenPayload.data.userName);
        }
    });

    $rootScope.$on('$routeChangeSuccess', function() {
        $rootScope.activePath = $location.path();
    });
});
