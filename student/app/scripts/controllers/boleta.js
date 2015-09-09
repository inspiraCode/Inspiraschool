'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:BoletaCtrl
 * @description
 * # BoletaCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('BoletaCtrl', function($scope, $routeParams, $window) {
    $scope.print = function() {
        $window.document.close();
        $window.print();
    };
    $scope.userID = $routeParams.userId;
    $scope.baseEntity = {
        fecha: 'miercoles, 9 de septiembre de 2015',
        hora: '08:58:38 a. m.',
        periodo: '2015 MAYO-AGOSTO',
        matricula: '22015',
        nombreAlumno: 'ORTIZ IBARRA GRISELDA',
        promedio: 9.8,
        materias: [{
            nombreMateria: 'ORGANIZACIÓN Y PROCEDIMIENTOS ADMINISTRATIVOS',
            calificacion: 10,
            Extraordinario: ''
        }, {
            nombreMateria: 'COMERCIO EXTERIOR',
            calificacion: 10,
            Extraordinario: ''
        }, {
            nombreMateria: 'PRACTICAS CONTABLES',
            calificacion: 10,
            Extraordinario: ''
        }, {
            nombreMateria: 'ETICA PROFESIONAL',
            calificacion: 9,
            Extraordinario: ''
        }, {
            nombreMateria: 'ANALISIS Y EVALUACION DE PROYECTOS DE INVERSION',
            calificacion: 10,
            Extraordinario: ''
        }, ]
    };
});
