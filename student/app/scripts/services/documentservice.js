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
            Title: 'Boleta',
            HRef: '',
            IMGHref: 'images/calendar.png',
            View: 'boleta'
        }, {
            id: 1,
            Title: 'Constancia',
            HRef: '',
            IMGHref: 'images/logo.png',
            View: 'constancia'
        }, {
            id: 2,
            Title: 'Constancia IMSS',
            HRef: '',
            IMGHref: 'images/logo.png',
            View: 'constanciaIMSS'
        }, {
            id: 3,
            Title: 'Constancia Consulado EUA',
            HRef: '',
            IMGHref: 'images/logo.png',
            View: 'constanciaCONSU_EUA'
        }

    ]);

    return crudInstance;
});
