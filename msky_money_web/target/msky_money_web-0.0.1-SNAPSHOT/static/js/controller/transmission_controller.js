'use strict';
 
App.controller('TransmissionController', ['$scope', 'TransmissionService', function($scope, TransmissionService) {
          var self = this;
          //self.user={id:null,username:'',address:'',email:''};
          self.transmissions=[];
               
          self.fetchAllTransmissions = function(){
        	  console.log("=====");
        	  TransmissionService.fetchAllTransmissions()
                  .then(
                               function(d) {
                                    self.transmissions = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching Transi');
                                }
                       );
          };
            
          self.fetchAllTransmissions();
 
      }]);