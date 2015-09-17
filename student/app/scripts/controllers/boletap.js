'use strict';

/**
 * @ngdoc function
 * @name studentApp.controller:BoletapCtrl
 * @description
 * # BoletapCtrl
 * Controller of the studentApp
 */
angular.module('studentApp').controller('BoletapCtrl', function($scope, $routeParams, $window, $activityIndicator, boletaService) {
    $activityIndicator.stopAnimating();
    alertify.closeAll();

    $scope.print = function() {
        $window.document.close();
        $window.print();
    };

    $scope.baseEntity = [{
        "id": "835",
        "report_date": new Date(),
        "period": "MAYO-AGOSTO",
        "grade": "LCP-IX",
        "enroll_number": "22015",
        "student_name": "ORTIZ IBARRA GRISELDA",
        "student_lastname": null,
        "metter_name": "ANALISIS Y EVALUACION DE PROYECTOS DE INVERSION",
        "partial_one": "10",
        "partial_two": "0",
        "special_one": "0",
        "special_two": "0"
    }, {
        "id": "835",
        "report_date": "2015-09-16 15:25:33",
        "period": "MAYO-AGOSTO",
        "grade": "LCP-IX",
        "enroll_number": "22015",
        "student_name": "ORTIZ IBARRA GRISELDA",
        "student_lastname": null,
        "metter_name": "COMERCIO EXTERIOR",
        "partial_one": "10",
        "partial_two": "0",
        "special_one": "0",
        "special_two": "0"
    }, {
        "id": "835",
        "report_date": "2015-09-16 15:25:33",
        "period": "MAYO-AGOSTO",
        "grade": "LCP-IX",
        "enroll_number": "22015",
        "student_name": "ORTIZ IBARRA GRISELDA",
        "student_lastname": null,
        "metter_name": "ETICA PROFESIONAL",
        "partial_one": "9",
        "partial_two": "0",
        "special_one": "0",
        "special_two": "0"
    }, {
        "id": "835",
        "report_date": "2015-09-16 15:25:33",
        "period": "MAYO-AGOSTO",
        "grade": "LCP-IX",
        "enroll_number": "22015",
        "student_name": "ORTIZ IBARRA GRISELDA",
        "student_lastname": null,
        "metter_name": "ORGANIZACI\u00d3N Y PROCEDIMIENTOS ADMINISTRATIVOS",
        "partial_one": "10",
        "partial_two": "0",
        "special_one": "0",
        "special_two": "0"
    }, {
        "id": "835",
        "report_date": "2015-09-16 15:25:33",
        "period": "MAYO-AGOSTO",
        "grade": "LCP-IX",
        "enroll_number": "22015",
        "student_name": "ORTIZ IBARRA GRISELDA",
        "student_lastname": null,
        "metter_name": "PRACTICAS CONTABLES",
        "partial_one": "10",
        "partial_two": "0",
        "special_one": "0",
        "special_two": "0"
    }];
    $scope.getAverage = function() {
        var matters = $scope.baseEntity;
        var sumPartialsOne = 0;
        var mattersCount = matters.length;
        for (var i = 0; i < matters.length; i++) {
            var current = matters[i];
            sumPartialsOne += Number(current.partial_one);
        };
        return sumPartialsOne / mattersCount;
    };
});
