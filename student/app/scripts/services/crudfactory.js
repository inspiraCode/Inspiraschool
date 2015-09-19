'use strict';

/**
 * @ngdoc service
 * @name studentApp.crudFactory
 * @description
 * # crudFactory
 * Factory in the studentApp.
 */
angular.module('studentApp').factory('crudFactory', function($http, $q, appConfig, $timeout, validatorService) {
    //Class for create Catalog objects, which will be used on select controls
    function ClassCatalog() {
        this._arrAllRecords = [];
        this.getAll = function() {
            return this._arrAllRecords;
        };
        this.getById = function(theId) {
            for (var i = 0; i < this._arrAllRecords.length; i++) {
                if (theId == this._arrAllRecords[i].id) {
                    return this._arrAllRecords[i];
                }
            }
            return {
                id: -1,
                value: ''
            };
        };
    };

    return function(oConfig) {

        ////////////////////INIT CONFIG
        var _entityName = oConfig.entityName;
        var _entityDefinition = oConfig.entityDefinition;

        var _catalogs;
        var createCatalogs = function(arrCatalogNames) {
            _catalogs = {};
            for (var i = 0; i < arrCatalogNames.length; i++) {
                var current = arrCatalogNames[i];
                _catalogs[current] = new ClassCatalog(current);
            };
        };
        createCatalogs(oConfig.catalogs);

        var _adapter = oConfig.adapter;
        var _adaptFromServer = oConfig.adaptFromServer;
        var _adaptToServer = oConfig.adaptToServer;
        var _arrDependencies = oConfig.dependencies;
        var _parentField = oConfig.parentField;
        /////////////////////END CONFIG

        var _arrAllRecords = [];

        var _create = function() {
            var oNewEntity = {};

            //System Fields
            for (var prop in _entityDefinition.systemFields) {
                if (_entityDefinition.systemFields.hasOwnProperty(prop)) {
                    oNewEntity[prop] = validatorService.getDefaultValueForType(_entityDefinition.systemFields[prop]);
                }
            }

            //Optional Fields
            for (var prop in _entityDefinition.optionalFields) {
                if (_entityDefinition.optionalFields.hasOwnProperty(prop)) {
                    oNewEntity[prop] = validatorService.getDefaultValueForType(_entityDefinition.optionalFields[prop]);
                }
            }

            //Required Fields
            for (var prop in _entityDefinition.requiredFields) {
                if (_entityDefinition.requiredFields.hasOwnProperty(prop)) {
                    oNewEntity[prop] = validatorService.getDefaultValueForType(_entityDefinition.requiredFields[prop]);
                }
            }

            return oNewEntity;
        };

        var _getById = function(theId) {
            for (var i = 0; i < _arrAllRecords.length; i++) {
                if (theId == _arrAllRecords[i].id) {
                    return _adapter(_arrAllRecords[i]);
                }
            }
            return null;
        };

        var _getByParentId = function(theParentId) {
            var result = [];
            for (var i = 0; i < _arrAllRecords.length; i++) {
                if (theParentId == _arrAllRecords[i][_parentField]) {
                    result.push(_adapter(_arrAllRecords[i]));
                }
            }
            return result;
        };

        var _getAll = function() {
            for (var i = 0; i < _arrAllRecords.length; i++) {
                _arrAllRecords[i] = _adapter(_arrAllRecords[i]);
            }
            return _arrAllRecords;
        };

        var _setAll = function(arrAll) {
            angular.copy(arrAll, _arrAllRecords);
        };

        var _validate = function(oEntity) {
            return true;
        };

        var _save = function(theEntity, theArrayBelonging) {
            if (_validate(theEntity)) {

                // New Entity
                if (theEntity.id < 1) {

                    var req = {
                        method: 'POST',
                        url: appConfig.API_URL + _entityName,
                        headers: {
                            // REMOVE CONTENT TYPE DUE TO CORS Acceptance.
                            'Content-Type': undefined
                        },
                        data: theEntity
                    };

                    // Simple POST request example (passing data) :
                    return $http(req).then(function(response) {
                        if (typeof response.data === 'object') {
                            var backendResponse = response.data;
                            if (!backendResponse.ErrorThrown) {
                                theEntity.id = backendResponse.Result.id;
                                if (angular.isArray(theArrayBelonging)) {
                                    var theEntityCopy = angular.copy(theEntity);
                                    _arrAllRecords.push(theEntityCopy);
                                    theArrayBelonging.push(theEntity);
                                } else {
                                    _arrAllRecords.push(theEntity);
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
                        alertify.alert('Error: ' + response.statusText).set('modal', true);
                        console.debug(response);
                        return $q.reject(response.data);
                    });


                } else { // Update Entity

                    var req = {
                        method: 'PUT',
                        url: appConfig.API_URL + _entityName + '/' + theEntity.id,
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
                                var current = _getById(theEntity.id);
                                if (!angular.equals(theEntity, current)) {
                                    angular.copy(theEntity, current);
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
                        alertify.alert('Error: ' + response.statusText).set('modal', true);
                        console.debug(response);
                        return $q.reject(response.data);
                    });
                }
                return false;
            }
            return false;
        };
        // var _saveBatchSerial = function(arrEntities, index, callBackSuccess, callBackError, callBackComplete) {
        //     if (arrEntities[i]) {
        //         _save(arrEntities[i]).then(function(data) {
        //             callBackSuccess(data);
        //         }, function(data) {
        //             callBackError(data);
        //         }).finally(function() {
        //             index++;
        //             _saveBatchSerial(arrEntities, index, callBackSuccess, callBackError, callba);
        //         });
        //     }
        //     callBackComplete();
        // };
        var _addBatch = function(addQty, theArrayBelonging) {
            var promises = [];
            for (var i = 0; i < addQty; i++) {
                var oEntityToCreate = _create();
                var promise = _save(oEntityToCreate, theArrayBelonging);
                promises.push(promise);
            }
            return $q.all(promises);
        };
        var _remove = function(theEntity, theArrayBelonging) {
            return $http.delete(appConfig.API_URL + _entityName + '/' + theEntity.id)
                .then(function(response) {
                    if (typeof response.data === 'object') {
                        var backendResponse = response.data;
                        if (!backendResponse.ErrorThrown) {
                            for (var i = 0; i < _arrAllRecords.length; i++) {
                                if (_arrAllRecords[i].id == theEntity.id) {
                                    _arrAllRecords.splice(i, 1);
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
                    alertify.alert('Error: ' + response.statusText).set('modal', true);
                    console.debug(response);
                    return $q.reject(response.data);
                });
        };

        var _removeSelected = function(arrEntities) {
            var arrItems;
            if (arrEntities) {
                arrItems = arrEntities;
            } else {
                arrItems = _arrAllRecords;
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
                var promise = _remove(oEntity, arrEntities);
                promises.push(promise);
            };

            return $q.all(promises);
        };

        var _loadEntity = function(qParams) {
            if (qParams === undefined || qParams == null) {
                qParams = '?';
            }

            var deferred = $q.defer();

            var req = {
                method: 'GET',
                url: appConfig.API_URL + _entityName,
                headers: {
                    // REMOVE CONTENT TYPE DUE TO CORS Acceptance.
                    'Content-Type': undefined
                }
            };

            // Simple POST request example (passing data) :
            $http(req).success(function(data) {
                var backendResponse = _adapter(data);
                if (backendResponse.ErrorThrown) {
                    deferred.reject(data);
                } else {
                    _adapter(backendResponse.Result);
                    deferred.resolve(backendResponse.Result);
                }
            }).error(function(data) {
                // something went wrong
                alertify.alert(data).set('modal', true);
                deferred.reject(response);
            });
            return deferred.promise;
        };

        var _loadEntitiesExecuted = false;
        var _loadCatalogsExecuted = false;

        var _loadEntities = function(bForce) {
            var deferred = $q.defer();

            if (bForce) _loadEntitiesExecuted = false;
            if (_loadEntitiesExecuted) {
                deferred.resolve();
            }
            _arrAllRecords = [];

            var req = {
                method: 'GET',
                url: appConfig.API_URL + _entityName,
                headers: {
                    // REMOVE CONTENT TYPE DUE TO CORS Acceptance.
                    'Content-Type': undefined
                }
            };
            
            $http(req).success(function(data) {
                    var backendResponse = data;
                    if (backendResponse.ErrorThrown) {
                        console.debug(response);
                        deferred.reject(data);
                    } else {
                        _arrAllRecords = backendResponse.Result;
                        for (var i = 0; i < _arrAllRecords.length; i++) {
                            _adapter(_arrAllRecords[i]);
                        };
                        _loadEntitiesExecuted = true;
                        deferred.resolve(_arrAllRecords);
                    }
                })
                .error(function(data) {
                    // something went wrong
                    console.debug(data);
                    deferred.reject(data);
                });

                return deferred.promise;
        };

        var _loadCatalogs = function(bForce) {
            if (bForce) _loadCatalogsExecuted = false;
            if (_loadCatalogsExecuted) {
                return $q(function(resolve, reject) {
                    resolve();
                });
            }

            var bAtLeastOneCatalog = false;
            for (var catalog in _catalogs) {
                if (_catalogs.hasOwnProperty(catalog)) {
                    bAtLeastOneCatalog = true;
                    _catalogs[catalog]._arrAllRecords = [];
                }
            }

            if (bAtLeastOneCatalog) {
                return $http.get(appConfig.API_URL + _entityName + '/getCatalogs')
                    .success(function(data) {
                        var backendResponse = data;
                        if (backendResponse.ErrorThrown) {
                            console.debug(response);
                            return $q.reject(data);
                        } else {
                            for (var catalog in _catalogs) {
                                if (_catalogs.hasOwnProperty(catalog)) {
                                    _catalogs[catalog]._arrAllRecords = backendResponse.Result[catalog];
                                }
                            }
                            _loadCatalogsExecuted = true;
                            return data;
                        }
                    })
                    .error(function(data) {
                        // something went wrong
                        console.debug(data);
                        return $q.reject(data);
                    });
            } else {
                return $q.resolve();
            }
        };

        var _loadAll = function(bForce) {
            var promises = [];
            for (var i = 0; i < _arrDependencies.length; i++) {
                if (_arrDependencies[i].hasOwnProperty('loadCatalogs')) {
                    var promiseCatalogs = _arrDependencies[i].loadCatalogs(bForce);
                    promises.push(promiseCatalogs);
                }
                var promiseEntities = _arrDependencies[i].loadEntities(bForce);
                promises.push(promiseEntities);
            }
            return $q.all(promises);
        };

        var _readByParentId = function(parentKey) {
            var result = [];
            var deferred = $q.defer();

            $http.get(appConfig.API_URL + _entityName + '?parentKey=' + parentKey)
                .then(
                    /*success*/
                    function(response) {
                        var backendResponse = response.data;
                        if (backendResponse.ErrorThrown) {
                            alertify.alert(backendResponse.ResponseDescription).set('modal', true);
                            deferred.reject(response);
                        } else {
                            for (var i = 0; i < backendResponse.Result.length; i++) {
                                _adapter(backendResponse.Result[i]);
                            }
                            deferred.resolve(backendResponse.Result);
                        }
                    },
                    /*error*/
                    function(response) {
                        alertify.alert('An error has occurred, see console for more details.').set('modal', true);
                        console.debug(response);
                        deferred.reject(response);
                    });

            return deferred.promise;
        };

        // Public API here
        var oAPI = {
            //Local scripts
            entityName: _entityName,
            create: _create,
            validate: _validate,
            getById: _getById,
            getByParentId: _getByParentId,
            getAll: _getAll,
            setAll: _setAll,
            catalogs: _catalogs,

            //Server transactions:
            save: _save,
            addBatch: _addBatch,
            remove: _remove,
            removeSelected: _removeSelected,
            loadCatalogs: _loadCatalogs,
            loadEntities: _loadEntities,
            loadEntity: _loadEntity,
            loadAll: _loadAll,
            readByParentId: _readByParentId
        };
        _arrDependencies.push(oAPI);
        return oAPI;
    };
});
