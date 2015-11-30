'use strict';
 
App.factory('TransmissionService', ['$http', '$q', function($http, $q){
 
    return {
         
            fetchAllTransmissions: function() {
                    return $http.get('http://localhost:8080/msky_money_web/transmission/')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching users');
                                        return $q.reject(errResponse);
                                    }
                            );
            }
    };
 
}]);