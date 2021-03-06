'use strict';

/**
 * @ngdoc service
 * @name teacherApp.documentService
 * @description
 * # documentService
 * Service in the teacherApp.
 */
angular.module('teacherApp').service('documentService', function(crudFactory, $rootScope) {
    var crudInstance = new crudFactory({
        //Entity Name = WebService/API to call:
        entityName: "",

        //Entity Definition = For validate entity, for create new object instances, 
        entityDefinition: {
            systemFields: {
                id: 'catalog',
                HRef: 'string',
                Title: 'string',
                View: 'string'
            },

            calculatedFields: {},

            optionalFields: {},

            requiredFields: {}
        },

        parentField: '',

        catalogs: [],

        adapter: function(theEntity) {
            theEntity.currentUser = $rootScope.loggedUser;
            theEntity.HRef = '#/' + theEntity.View;

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
