'use strict';

/**
 * @ngdoc overview
 * @name studentApp
 * @description
 * # studentApp
 *
 * Main module of the application.
 */
angular.module('studentApp', [
    'ngAnimate',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'angular-jwt',
    'angular-storage',
    'ngActivityIndicator'
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
        .when('/Logout', {
          templateUrl: 'views/login.html',
          controller: 'LogoutCtrl',
          controllerAs: 'Logout'
        })
        .when('/boleta', {
          templateUrl: 'views/boleta.html',
          controller: 'BoletaCtrl',
          controllerAs: 'boleta'
        })
        .when('/constancia', {
          templateUrl: 'views/constancia.html',
          controller: 'ConstanciaCtrl',
          controllerAs: 'constancia'
        })
        .otherwise({
            redirectTo: '/'
        });
    $activityIndicatorProvider.setActivityIndicatorStyle('CircledWhite');
    alertify.set('notifier', 'position', 'bottom-left');
    jwtInterceptorProvider.tokenGetter = function(store) {
        return store.get('jwt');
    };
    $httpProvider.interceptors.push('jwtInterceptor');
    // Enable CORS domain calls
    $httpProvider.defaults.useXDomain = true;
}).run(function($rootScope, store, jwtHelper, LoginService) {
    $rootScope.loggedUser = LoginService.data;
    $rootScope.$on('$stateChangeStart', function(e, to) {
        if (to.data && to.data.requiresLogin) {
            var jwt = store.get('jwt');
            if (!jwt || jwtHelper.isTokenExpired(jwt)) {
                e.preventDefault();
                $rootScope.go('/login');
            } else {
                var tokenPayload = jwtHelper.decodeToken(jwt);
                LoginService.update(tokenPayload.data.userId, tokenPayload.data.userName);
            }
        }
    });
});
