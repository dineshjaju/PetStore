'use strict';
 
angular.module('myApp').factory('PetService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8080/pet/';
 
    var config = {
    		 headers : {'Accept' : 'application/json',
    			 'Content-Type': 'application/json' }
    		};

    
    var factory = {
        fetchAllpets: fetchAllpets,
        createpet: createpet,
        updatepet:updatepet,
        deletepet:deletepet
    };
 
    return factory;
    
    $http.defaults.headers.common['Content-Type'] = 'application/json; charset=utf-8';
    
    function fetchAllpets() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI,config)
            .then(
            function (response) {
                deferred.resolve(response.data);
                console.log(response.data);
            },
            function(errResponse){
                console.error('Error while fetching pets');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    
    function createpet(pet) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, pet)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating pet');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
 
    function updatepet(pet, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, pet)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating pet');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deletepet(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting pet');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
}]);