'use strict';

/**
 * @ngdoc service
 * @name teacherApp.noteService
 * @description
 * # noteService
 * Service in the teacherApp.
 */
angular.module('teacherApp').service('noteService', function(crudFactory) {
    var crudInstance = new crudFactory({
        //Entity Name = WebService/API to call:
        entityName: "note",

        //Entity Definition = For validate entity, for create new object instances, 
        entityDefinition: {
            systemFields: {
                id: 'catalog',
                enroll_number: 'string',
                student_name: 'string',
                partial_one: 'number',
                partial_two: 'number',
                extraordinary_one: 'number',
                extraordinary_two: 'number'
            },

            calculatedFields: {
                averageNote: 'number'
            },

            optionalFields: {},

            requiredFields: {}
        },

        parentField: '',

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

        dependencies: []
    });
    crudInstance.setAll([{
            id: 1,
            enroll_number: '2103926',
            student_name: 'ADAME VILLANUEVA RAYMUNDA',
            partial_one: 8,
            partial_two: 9,
            extraordinary_one: 0,
            extraordinary_two: 0
        }, {
            id: 2,
            enroll_number: '2103941',
            student_name: 'AGUILAR VEGA FIDENCIA',
            partial_one: 8,
            partial_two: 9,
            extraordinary_one: 0,
            extraordinary_two: 0
        }, {
            id: 3,
            enroll_number: '2103950',
            student_name: 'ALAVEZ SANTIAGO MARTHA LIZBET',
            partial_one: 10,
            partial_two: 10,
            extraordinary_one: 0,
            extraordinary_two: 0
        }, {
            id: 4,
            enroll_number: '2103989',
            student_name: 'AYALA MENDOZA RUTH BERENICE',
            partial_one: 8,
            partial_two: 8,
            extraordinary_one: 0,
            extraordinary_two: 0
        }, {
            id: 5,
            enroll_number: '2103994',
            student_name: 'CALDERON DELGADO BERNARDO',
            partial_one: 8,
            partial_two: 9,
            extraordinary_one: 0,
            extraordinary_two: 0
        }, {
            id: 6,
            enroll_number: '1515077',
            student_name: 'CARRILLO PIEDRA PATRICIA',
            partial_one: 9,
            partial_two: 10,
            extraordinary_one: 0,
            extraordinary_two: 0
        },

    ]);

    return crudInstance;
});
