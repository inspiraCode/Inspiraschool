'use strict';

/**
 * @ngdoc service
 * @name teacherApp.courseService
 * @description
 * # courseService
 * Service in the teacherApp.
 */
angular.module('teacherApp').service('courseService', function(crudFactory) {
    var crudInstance = new crudFactory({
        //Entity Name = WebService/API to call:
        entityName: "maestro_materias",

        entityDefinition: {
            systemFields: {
                id: 'catalog',
            },

            calculatedFields: {},

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

    crudInstance.setSelected = function(theEntity) {
        crudInstance.selectedEntity = theEntity;
    };

    return crudInstance;
});
