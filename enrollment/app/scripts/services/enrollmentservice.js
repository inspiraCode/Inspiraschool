'use strict';

/**
 * @ngdoc service
 * @name enrollmentApp.enrollmentService
 * @description
 * # enrollmentService
 * Service in the enrollmentApp.
 */
angular.module('enrollmentApp').service('enrollmentService', function(crudFactory, $q) {
    var crudInstance = new crudFactory({
        //Entity Name = WebService/API to call:
        entityName: "subscribe",

        //Entity Definition = For validate entity, for create new object instances, 
        entityDefinition: {
            systemFields: {
                id: 'catalog',
                folio: 'catalog',
            },

            calculatedFields: {},

            optionalFields: {},

            requiredFields: {
                shift: "string",
                course: "string",
                course_plan: "string",

                city: "string",
                email: "email",
                first_name: "string",
                gender: "string",
                last_name: "string",
                state: "string",
                birth
            }
        },

        catalogs: [],

        adapter: function(theEntity) {
            return theEntity;
        },

        adaptFromServer: function(theEntity) {
            //theEntity.QuoteDue = moment(theEntity.QuoteDue, moment.ISO_8601).format('MM/DD/YYYY');
        },

        adaptToServer: function(theEntity) {
            //self.validate(theEntity);
        },

        loadAll: function(bForce) {
            return $q.all([
                this.loadEntities(bForce)
            ]);
        }
    });

    //overwrite catalogs since they are not being pulled from server:
    crudInstance.catalogs = {
        Shifts: {
            _arrAllRecords: [{
                id: 0,
                Value: '',
                shift_code: ''
            }, {
                id: 1,
                Value: 'Dominical',
                shift_code: 'd'
            }, {
                id: 2,
                Value: 'Empresarial',
                shift_code: 'e'
            }, {
                id: 3,
                Value: 'Sabatico',
                shift_code: 's'
            }, {
                id: 4,
                Value: 'Virtual',
                shift_code: 'v'
            }],
            getAll: function() {
                return this._arrAllRecords;
            },
            getById: function(theId) {
                for (var i = 0; i < this._arrAllRecords.length; i++) {
                    if (theId == this._arrAllRecords[i].id) {
                        return this._arrAllRecords[i];
                    }
                }
                return this._arrAllRecords[0];
            }
        },
        Courses: {
            _arrAllRecords: [{
                id: 0,
                Value: '',
                shift_code: '',
                course_code: ''
            }, {
                id: 1,
                Value: 'Preparatoria',
                shift_code: 'd',
                course_code: 'pp'
            }, {
                id: 2,
                Value: 'Universidad',
                shift_code: 'd',
                course_code: 'un'
            }, {
                id: 3,
                Value: 'Diplomado',
                shift_code: 'd',
                course_code: 'dp'
            }, {
                id: 4,
                Value: 'Preparatoria',
                shift_code: 'e',
                course_code: 'pp'
            }, {
                id: 5,
                Value: 'Universidad',
                shift_code: 'e',
                course_code: 'un'
            }, {
                id: 6,
                Value: 'Diplomado',
                shift_code: 'e',
                course_code: 'dp'
            }, {
                id: 7,
                Value: 'Preparatoria',
                shift_code: 's',
                course_code: 'pp'
            }, {
                id: 8,
                Value: 'Universidad',
                shift_code: 's',
                course_code: 'un'
            }, {
                id: 9,
                Value: 'Diplomado',
                shift_code: 's',
                course_code: 'dp'
            }, {
                id: 10,
                Value: 'Preparatoria',
                shift_code: 'v',
                course_code: 'pp'
            }, {
                id: 11,
                Value: 'Universidad',
                shift_code: 'v',
                course_code: 'un'
            }, {
                id: 12,
                Value: 'Diplomado',
                shift_code: 'v',
                course_code: 'dp'
            }],
            getAll: function() {
                return this._arrAllRecords;
            },
            getById: function(theId) {
                for (var i = 0; i < this._arrAllRecords.length; i++) {
                    if (theId == this._arrAllRecords[i].id) {
                        return this._arrAllRecords[i];
                    }
                }
                return this._arrAllRecords[0];
            }
        },

        CoursePlans: {
            _arrAllRecords: [{
                id: 0,
                plan_code: '',
                Value: '',
                course_code: ''
            }, {
                id: 1,
                plan_code: 'LEAE',
                Value: 'Lic. En Administración de Empresas',
                course_code: 'un'
            }, {
                id: 2,
                plan_code: 'LECP',
                Value: 'Lic. En Contaduría Pública',
                course_code: 'un'
            }, {
                id: 3,
                plan_code: 'LEDP',
                Value: 'Lic. En Derecho',
                course_code: 'un'
            }, {
                id: 4,
                plan_code: 'BGPS',
                Value: 'Bachillerato General Plan Semestral',
                course_code: 'pp'
            }, {
                id: 5,
                plan_code: 'BGPT',
                Value: 'Bachillerato General Plan Tetramestral',
                course_code: 'pp'
            }, {
                id: 6,
                plan_code: 'DPAE',
                Value: 'Dimplomado en Asistencia Educativa',
                course_code: 'dp'
            }, {
                id: 7,
                plan_code: 'DAFS',
                Value: 'Diplomado en Asistencia Familiar y de Salud',
                course_code: 'dp'
            }],
            getAll: function() {
                return this._arrAllRecords;
            },
            getById: function(theId) {
                for (var i = 0; i < this._arrAllRecords.length; i++) {
                    if (theId == this._arrAllRecords[i].id) {
                        return this._arrAllRecords[i];
                    }
                }
                return null;
            }
        }
    };

    return crudInstance;
});
