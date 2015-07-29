'use strict';

/**
 * @ngdoc service
 * @name enrollmentApp.enrollmentService
 * @description
 * # enrollmentService
 * Service in the enrollmentApp.
 */
angular.module('enrollmentApp').service('enrollmentService', function($http, $q, appConfig, $timeout, validatorService) {

    var self = this;
    var entityName = "subscribe";
    var arrAllRecords = [];

    var entityDefinition = {
        systemFields: {
            id: 'catalog',
            folio: 'catalog',
        },

        calculatedFields: {},

        optionalFields: {},
        requiredFields: {
            SupplierName: 'string',
            ContactName: 'string',
            ContactEmail: 'email',
            Visible: 'boolean',
            city: "string",
            course: "string",
            course_plan: "string",
            email: "email",
            first_name: "string",
            gender: "string",
            last_name: "string",
            shift: "string",
            state: "string"
        }
    };

    this.create = function() {
        var oNewEntity = {};

        //System Fields
        for (var prop in entityDefinition.systemFields) {
            if (entityDefinition.systemFields.hasOwnProperty(prop)) {
                oNewEntity[prop] = validatorService.getDefaultValueForType(entityDefinition.systemFields[prop]);
            }
        }

        //Optional Fields
        for (var prop in entityDefinition.optionalFields) {
            if (entityDefinition.optionalFields.hasOwnProperty(prop)) {
                oNewEntity[prop] = validatorService.getDefaultValueForType(entityDefinition.optionalFields[prop]);
            }
        }

        //Required Fields
        for (var prop in entityDefinition.requiredFields) {
            if (entityDefinition.requiredFields.hasOwnProperty(prop)) {
                oNewEntity[prop] = validatorService.getDefaultValueForType(entityDefinition.requiredFields[prop]);
            }
        }

        return oNewEntity;
    };

    this.catalogs = {
        Shifts: {
            arrAllRecords: [{
                id: 0,
                Value: '',
                shift_code: ''
            }, {
                id: 1,
                Value: 'Dominical',
                shift_code: 'd'
            }, {
                id: 2,
                Value: 'Empresarial',
                shift_code: 'e'
            }, {
                id: 3,
                Value: 'Sabatico',
                shift_code: 's'
            }, {
                id: 4,
                Value: 'Virtual',
                shift_code: 'v'
            }],
            getAll: function() {
                return this.arrAllRecords;
            },
            getById: function(theId) {
                for (var i = 0; i < this.arrAllRecords.length; i++) {
                    if (theId == this.arrAllRecords[i].id) {
                        return this.arrAllRecords[i];
                    }
                }
                return this.arrAllRecords[0];
            }
        },
        Courses: {
            arrAllRecords: [{
                id: 0,
                Value: '',
                shift_code: '',
                course_code: ''
            }, {
                id: 1,
                Value: 'Preparatoria',
                shift_code: 'd',
                course_code: 'pp'
            }, {
                id: 2,
                Value: 'Universidad',
                shift_code: 'd',
                course_code: 'un'
            }, {
                id: 3,
                Value: 'Diplomado',
                shift_code: 'd',
                course_code: 'dp'
            }, {
                id: 4,
                Value: 'Preparatoria',
                shift_code: 'e',
                course_code: 'pp'
            }, {
                id: 5,
                Value: 'Universidad',
                shift_code: 'e',
                course_code: 'un'
            }, {
                id: 6,
                Value: 'Diplomado',
                shift_code: 'e',
                course_code: 'dp'
            }, {
                id: 7,
                Value: 'Preparatoria',
                shift_code: 's',
                course_code: 'pp'
            }, {
                id: 8,
                Value: 'Universidad',
                shift_code: 's',
                course_code: 'un'
            }, {
                id: 9,
                Value: 'Diplomado',
                shift_code: 's',
                course_code: 'dp'
            }, {
                id: 10,
                Value: 'Preparatoria',
                shift_code: 'v',
                course_code: 'pp'
            }, {
                id: 11,
                Value: 'Universidad',
                shift_code: 'v',
                course_code: 'un'
            }, {
                id: 12,
                Value: 'Diplomado',
                shift_code: 'v',
                course_code: 'dp'
            }],
            getAll: function() {
                return this.arrAllRecords;
            },
            getById: function(theId) {
                for (var i = 0; i < this.arrAllRecords.length; i++) {
                    if (theId == this.arrAllRecords[i].id) {
                        return this.arrAllRecords[i];
                    }
                }
                return this.arrAllRecords[0];
            }
        },

        CoursePlans: {
            arrAllRecords: [{
                id: 0,
                plan_code: '',
                Value: '',
                course_code: ''
            }, {
                id: 1,
                plan_code: 'LEAE',
                Value: 'Lic. En Administración de Empresas',
                course_code: 'un'
            }, {
                id: 2,
                plan_code: 'LECP',
                Value: 'Lic. En Contaduría Pública',
                course_code: 'un'
            }, {
                id: 3,
                plan_code: 'LEDP',
                Value: 'Lic. En Derecho',
                course_code: 'un'
            }, {
                id: 4,
                plan_code: 'BGPS',
                Value: 'Bachillerato General Plan Semestral',
                course_code: 'pp'
            }, {
                id: 5,
                plan_code: 'BGPT',
                Value: 'Bachillerato General Plan Tetramestral',
                course_code: 'pp'
            }, {
                id: 6,
                plan_code: 'DPAE',
                Value: 'Dimplomado en Asistencia Educativa',
                course_code: 'dp'
            }, {
                id: 7,
                plan_code: 'DAFS',
                Value: 'Diplomado en Asistencia Familiar y de Salud',
                course_code: 'dp'
            }],
            getAll: function() {
                return this.arrAllRecords;
            },
            getById: function(theId) {
                for (var i = 0; i < this.arrAllRecords.length; i++) {
                    if (theId == this.arrAllRecords[i].id) {
                        return this.arrAllRecords[i];
                    }
                }
                return null;
            }
        },
        ToDoCategorie: {
            arrAllRecords: [],
            getAll: function() {
                return this.arrAllRecords;
            },
            getById: function(theId) {
                for (var i = 0; i < this.arrAllRecords.length; i++) {
                    if (theId == this.arrAllRecords[i].id) {
                        return this.arrAllRecords[i];
                    }
                }
                return null;
            }
        },

    };

    var adapter = function(theEntity) {
        //Adapt entity from backend
        return theEntity
    };

    this.getById = function(theId) {
        for (var i = 0; i < arrAllRecords.length; i++) {
            if (theId == arrAllRecords[i].id) {
                return adapter(arrAllRecords[i]);
            }
        }
        return null;
    };

    this.getAll = function() {
        for (var i = 0; i < arrAllRecords.length; i++) {
            arrAllRecords[i] = adapter(arrAllRecords[i]);
        }
        return arrAllRecords;
    };

    this.remove = function(theEntity, theArrayBelonging) {
        return $http.delete(appConfig.API_URL + entityName + '/' + theEntity.id)
            .then(function(response) {
                if (typeof response.data === 'object') {
                    var backendResponse = response.data;
                    if (!backendResponse.ErrorThrown) {
                        for (var i = 0; i < arrAllRecords.length; i++) {
                            if (arrAllRecords[i].id == theEntity.id) {
                                arrAllRecords.splice(i, 1);
                                break;
                            }
                        }
                        if (angular.isArray(theArrayBelonging)) {
                            for (var i = 0; i < theArrayBelonging.length; i++) {
                                if (theArrayBelonging[i].id == theEntity.id) {
                                    theArrayBelonging.splice(i, 1);
                                    break;
                                }
                            }
                        }
                        $timeout(function() {
                            alertify.success(backendResponse.ResponseDescription);
                        }, 100);
                        return response.data;
                    } else {
                        alertify.alert(backendResponse.ResponseDescription).set('modal', true);
                        console.debug(response);
                        return $q.reject(response.data);
                    }
                } else {
                    // invalid response
                    alertify.alert('An error has occurred, see console for more details.').set('modal', true);
                    console.debug(response);
                    return $q.reject(response.data);
                }
            }, function(response) {
                // something went wrong
                alertify.alert('An error has occurred, see console for more details.').set('modal', true);
                console.debug(response);
                return $q.reject(response.data);
            });
    };

    this.removeSelected = function(anArray) {
        var arrItems;
        if (anArray) {
            arrItems = anArray;
        } else {
            arrItems = arrAllRecords;
        }

        var arrItemsToRemove = [];
        var promises = [];
        for (var i = arrItems.length - 1; i > -1; i--) {
            var current = arrItems[i];
            if (current.checked) {
                arrItemsToRemove.push(current);
            }
        }

        for (var j = 0; j < arrItemsToRemove.length; j++) {
            var oEntity = arrItemsToRemove[j];
            var promise = self.remove(oEntity, anArray);
            promises.push(promise);
        };

        return $q.all(promises);
    };

    this.addBatch = function(addQty, theArrayBelonging) {
        var promises = [];
        for (var i = 0; i < addQty; i++) {
            var oEntityToCreate = self.create();
            var promise = self.save(oEntityToCreate, theArrayBelonging);
            promises.push(promise);
        }
        return $q.all(promises);
    };

    this.save = function(theEntity, theArrayBelonging) {
        if (this.validate(theEntity)) {

            // New Entity
            if (theEntity.id < 1) {


                var req = {
                    method: 'POST',
                    url: appConfig.API_URL + entityName,
                    headers: {
                        // REMOVE CONTENT TYPE DUE TO CORS Acceptance.
                        'Content-Type': undefined
                    },
                    data: theEntity
                };

                return $http(req).then(function(response) {
                    if (typeof response.data === 'object') {
                        var backendResponse = response.data;
                        if (!backendResponse.ErrorThrown) {
                            theEntity.id = backendResponse.Result.id;
                            if (angular.isArray(theArrayBelonging)) {
                                var theEntityCopy = angular.copy(theEntity);
                                arrAllRecords.push(theEntityCopy);
                                theArrayBelonging.push(theEntity);
                            } else {
                                arrAllRecords.push(theEntity);
                            }
                            $timeout(function() {
                                alertify.success(backendResponse.ResponseDescription);
                            }, 100);
                            return response.data;
                        } else {
                            alertify.alert(backendResponse.ResponseDescription).set('modal', true);
                            console.debug(response);
                            return $q.reject(response.data);
                        }
                    } else {
                        // invalid response
                        alertify.alert('An error has occurred, see console for more details.').set('modal', true);
                        console.debug(response);
                        return $q.reject(response.data);
                    }
                }, function(response) {
                    // something went wrong
                    alertify.alert('An error has occurred, see console for more details.').set('modal', true);
                    console.debug(response);
                    return $q.reject(response.data);
                });

            } else { // Update Entity
                var req = {
                    method: 'PUT',
                    url: appConfig.API_URL + entityName + '/' + theEntity.id,
                    headers: {
                        // REMOVE CONTENT TYPE DUE TO CORS Acceptance.
                        'Content-Type': undefined
                    },
                    data: theEntity
                };
                return $http(req).then(function(response) {
                    if (typeof response.data === 'object') {
                        var backendResponse = response.data;
                        if (!backendResponse.ErrorThrown) {
                            theEntity.editMode = false;
                            var current = self.getById(theEntity.id);
                            angular.copy(theEntity, current);
                            $timeout(function() {
                                alertify.success(backendResponse.ResponseDescription);
                            }, 100);
                            return response.data;
                        } else {
                            alertify.alert(backendResponse.ResponseDescription).set('modal', true);
                            console.debug(response);
                            return $q.reject(response.data);
                        }
                    } else {
                        // invalid response
                        alertify.alert('An error has occurred, see console for more details.').set('modal', true);
                        console.debug(response);
                        return $q.reject(response.data);
                    }
                }, function(response) {
                    // something went wrong
                    alertify.alert('An error has occurred, see console for more details.').set('modal', true);
                    console.debug(response);
                    return $q.reject(response.data);
                });
            }
            return false;
        }
        return false;
    };

    this.validate = function(oEntity) {
        return true;
    };

    var loadEntitiesExecuted = false;
    var loadCatalogsExecuted = false;

    var adaptFromServer = function(theEntity) {
        //theEntity.QuoteDue = moment(theEntity.QuoteDue, moment.ISO_8601).format('MM/DD/YYYY');
    };
    var adaptToServer = function(theEntity) {
        //self.validate(theEntity);
    };
    this.loadEntities = function(bForce) {
        if (bForce) loadEntitiesExecuted = false;
        if (loadEntitiesExecuted) {
            return $q(function(resolve, reject) {
                resolve();
            });
        }
        arrAllRecords = [];

        return $http.get(appConfig.API_URL + entityName)
            .success(function(data) {
                var backendResponse = data;
                if (backendResponse.ErrorThrown) {
                    console.debug(response);
                    return $q.reject(data);
                } else {
                    arrAllRecords = backendResponse.Result;
                    for (var i = 0; i < arrAllRecords.length; i++) {
                        adaptFromServer(arrAllRecords[i]);
                    };
                    loadEntitiesExecuted = true;
                    return data;
                }
            })
            .error(function(data) {
                // something went wrong
                console.debug(data);
                return $q.reject(data);
            });
    };

    this.loadCatalogs = function(bForce) {
        if (bForce) loadCatalogsExecuted = false;
        if (loadCatalogsExecuted) {
            return $q(function(resolve, reject) {
                resolve();
            });
        }

        for (var catalog in self.catalogs) {
            if (self.catalogs.hasOwnProperty(catalog)) {
                self.catalogs[catalog].arrAllRecords = [];
            }
        }

        return $http.get(appConfig.API_URL + entityName + 'Catalog')
            .success(function(data) {
                var backendResponse = data;
                if (backendResponse.ErrorThrown) {
                    console.debug(response);
                    return $q.reject(data);
                } else {
                    for (var catalog in self.catalogs) {
                        if (self.catalogs.hasOwnProperty(catalog)) {
                            self.catalogs[catalog].arrAllRecords = backendResponse.Result[catalog];
                        }
                    }
                    loadCatalogsExecuted = true;
                    return data;
                }
            })
            .error(function(data) {
                // something went wrong
                console.debug(data);
                return $q.reject(data);
            });
    };

    this.loadAll = function(bForce) {
        return $q.all([
            //self.loadCatalogs(), catalogs are hardcoded
            self.loadEntities(bForce)
        ]);
    };
});
