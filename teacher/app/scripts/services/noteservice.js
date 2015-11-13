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
        entityName: "alumnos",

        //Entity Definition = For validate entity, for create new object instances, 
        entityDefinition: {
            systemFields: {
                id: 'catalog'
            },

            calculatedFields: {},

            optionalFields: {},

            requiredFields: {}
        },

        parentField: 'course_id',

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

    return crudInstance;
});
