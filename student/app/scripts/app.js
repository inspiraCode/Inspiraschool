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
    'ngActivityIndicator',
    'ngFx'
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
        .when('/boletap', {
            templateUrl: 'views/boletap.html',
            controller: 'BoletapCtrl',
            controllerAs: 'boletap'
        })
        .when('/constanciaCONSU_EUA', {
            templateUrl: 'views/constanciaconsu_eua.html',
            controller: 'ConstanciaconsuEuaCtrl',
            controllerAs: 'constanciaCONSUEUA'
        })
        .when('/constanciaIMSS', {
            templateUrl: 'views/constanciaimss.html',
            controller: 'ConstanciaimssCtrl',
            controllerAs: 'constanciaIMSS'
        })
        .when('/constanciaDMN', {
          templateUrl: 'views/constanciadmn.html',
          controller: 'ConstanciadmnCtrl',
          controllerAs: 'constanciaDMN'
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
