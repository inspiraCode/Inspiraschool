'use strict';

/**
 * @ngdoc service
 * @name enrollmentApp.validatorService
 * @description
 * # validatorService
 * Service in the enrollmentApp.
 */
angular.module('enrollmentApp').service('validatorService', function() {
    var self = this;
    this.isValidDate = function(value) {
        var sError = '';
        var theDate = moment(value, 'MM/DD/YYYY');
        if (theDate.isValid() == false) {
            sError = 'Invalid Date.';
        }
        var minDate = moment('02/10/1985', 'MM/DD/YYYY');
        var maxDate = moment('02/10/2200', 'MM/DD/YYYY');

        if (theDate.isBefore(minDate)) {
            sError = 'Date too old.';
        }
        if (theDate.isAfter(maxDate)) {
            sError = 'Date not allowed.';
        }
        return sError;
    };

    this.isValidString = function(value) {
        var sError = '';
        if (jQuery.trim(value) == '') {
            sError = 'Empty value.';
        }
        return sError;
    };

    this.isValidNumber = function(value) {
        var sError = '';
        if (!jQuery.isNumeric(value)) {
            sError = 'Invalid number.';
        }
        return sError;
    };

    this.isValidCatalog = function(value) {
        var sError = '';
        if (self.isValidNumber(value) != '' || value <= 0) {
            sError = 'Selection required.';
        }
        return sError;
    };

    this.isValidPhone = function(value) {
        var sError = 'Invalid Phone.';
        if (self.isValidString(value) == '') {
            if (value.length >= 10 && value.length <= 13) {
                sError = '';
            }
        }
        return sError;
    };

    this.isValidEmail = function(value) {
        var sError = '';
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        if (!re.test(value)) {
            sError = 'Invalid Email Address.';
        }
        return sError;
    };

    this.isValidBoolean = function(value) {
        var sError = 'Invalid value.';

        if (value === true || value === false) {
            sError = '';
        }
        return sError;
    };

    this.validate = function(value, kind) {
        var sError = '';
        switch (kind) {
            case 'string':
                sError = self.isValidString(value);
                break;
            case 'number':
                sError = self.isValidNumber(value);
                break;
            case 'date':
                sError = self.isValidDate(value);
                break;
            case 'catalog':
                sError = self.isValidCatalog(value);
                break;
            case 'phone':
                sError = self.isValidPhone(value);
                break;
            case 'email':
                sError = self.isValidEmail(value);
                break;
            case 'boolean':
                sError = self.isValidBoolean(value);
                break;
            default:
        }
        return sError;
    };

    this.getProgress = function(theEntity, requiredFields) {
        var totalFields = 0;
        var totalFieldsCompleted = 0;

        for (var field in requiredFields) {
            if (requiredFields.hasOwnProperty(field)) {
                totalFields++;
                var value = theEntity[field];
                if (self.validate(value, requiredFields[field]) == '') {
                    totalFieldsCompleted++;
                }
            }
        }

        if (theEntity.taskEntity && theEntity.taskEntity.ToDo) {
            for (var i = 0; i < theEntity.taskEntity.ToDo.length; i++) {
                var todo = theEntity.taskEntity.ToDo[i];
                totalFields++;
                if (todo.IsDone) {
                    totalFieldsCompleted++;
                }
            }
        }

        return totalFieldsCompleted / totalFields * 100;
    };

    this.getDefaultValueForType = function(sType) {
        var result;
        switch (sType) {
            case 'catalog':
                result = -1;
                break;
            case 'email':
            case 'phone':
            case 'string':
                result = '';
                break;
            case 'date':
                result = null;
                break;
            case 'boolean':
                result = false;
                break;
            case 'number':
                result = 0;
                break;
            case 'list':
                result = [];
                break;
        }
        return result;
    };
});
