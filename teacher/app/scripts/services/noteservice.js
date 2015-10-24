'use strict';

/**
 * @ngdoc service
 * @name teacherApp.noteService
 * @description
 * # noteService
 * Service in the teacherApp.
 */
angular.module('teacherApp').service('noteService', function(crudFactory, $q, $http, appConfig) {
    var crudInstance = new crudFactory({
        //Entity Name = WebService/API to call:
        entityName: "alumnos_calificacion",

        //Entity Definition = For validate entity, for create new object instances, 
        entityDefinition: {
            systemFields: {
                id: 'catalog'
            },

            calculatedFields: {},

            optionalFields: {},

            requiredFields: {}
        },

        parentField: 'id_metter_course',

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

    crudInstance.pullNotesPerCourse = function(parentKey) {
        var result = [];
        var deferred = $q.defer();

        $http.get(appConfig.API_URL + crudInstance.entityName + '?course_id=' + parentKey)
            .then(
                /*success*/
                function(response) {
                    var backendResponse = response.data;
                    if (backendResponse.ErrorThrown) {
                        alertify.alert(backendResponse.ResponseDescription).set('modal', true);
                        deferred.reject(response);
                    } else {
                        for (var i = 0; i < backendResponse.Result.length; i++) {
                            backendResponse.Result[i];
                        }
                        deferred.resolve(backendResponse.Result);
                    }
                },
                /*error*/
                function(response) {
                    alertify.alert('An error has occurred.').set('modal', true);
                    deferred.reject(response);
                });

        return deferred.promise;
    }
    return crudInstance;
});
