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
        entityName: "document",

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
    crudInstance.setAll([{
            id: 0,
            Title: 'Grupo 1A',
            HRef: '',
            IMGHref: 'images/calendar.png',
            View: 'boleta'
        }, {
            id: 1,
            Title: 'Grupo 2A',
            HRef: '',
            IMGHref: 'images/logo.png',
            View: 'constancia'
        }, {
            id: 2,
            Title: 'Grupo 3A',
            HRef: '',
            IMGHref: 'images/logo.png',
            View: 'constanciaIMSS'
        }, {
            id: 3,
            Title: 'Grupo 4A',
            HRef: '',
            IMGHref: 'images/logo.png',
            View: 'constanciaCONSU_EUA'
        }, {
            id: 4,
            Title: 'Grupo 5A',
            HRef: '',
            IMGHref: 'images/logo.png',
            View: 'constanciaDMN'
        }

    ]);

    return crudInstance;
});
