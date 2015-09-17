'use strict';

/**
 * @ngdoc service
 * @name studentApp.documentService
 * @description
 * # documentService
 * Service in the studentApp.
 */
angular.module('studentApp').service('documentService', function(crudFactory, $rootScope) {
    var crudInstance = new crudFactory({
        //Entity Name = WebService/API to call:
        entityName: "document",

        //Entity Definition = For validate entity, for create new object instances, 
        entityDefinition: {
            systemFields: {
                id: 'catalog',
                HRef: 'string',
                Name: 'string'
            },

            calculatedFields: {},

            optionalFields: {},

            requiredFields: {}
        },

        parentField: '',

        catalogs: [],

        adapter: function(theEntity) {
            theEntity.currentUser = $rootScope.loggedUser;
            theEntity.HRef = '#/' + theEntity.Name.toLowerCase();

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
            id: 0,
            Name: 'Boleta',
            HRef: '',
            IMGHref: 'images/calendar.png'
        }, {
            id: 1,
            Name: 'Constancia',
            HRef: '',
            IMGHref: 'images/logo.png'
        }, {
            id: 2,
            Name: 'Boletap',
            HRef: '',
            IMGHref: 'images/logo.png'
        }

    ]);

    return crudInstance;
});
