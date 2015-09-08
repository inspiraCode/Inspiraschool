'use strict';

/**
 * @ngdoc service
 * @name studentApp.boletaService
 * @description
 * # boletaService
 * Service in the studentApp.
 */
angular.module('studentApp').service('boletaService', function(crudFactory) {
    var crudInstance = new crudFactory({
        //Entity Name = WebService/API to call:
        entityName: "boleta",

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
