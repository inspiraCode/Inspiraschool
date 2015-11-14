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

        adapterIn: function(theEntity) {
            theEntity.partial_one = Number(theEntity.partial_one);
            theEntity.partial_two = Number(theEntity.partial_two);
            theEntity['final'] = Number(theEntity['final']);
            theEntity.id = Number(theEntity.id);
            theEntity.id_student = Number(theEntity.id_student);
        },

        adaptToServer: function(theEntity) {
            //self.validate(theEntity);
        },

        dependencies: []
    });

    return crudInstance;
});
