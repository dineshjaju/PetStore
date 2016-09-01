'use strict';
 
angular.module('myApp').controller('PetController', ['$scope', 'PetService', function($scope, PetService) {
	var self = this;
    self.pet={id:null,name:''};
    self.pets=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllpets();
 
    function fetchAllpets(){
        PetService.fetchAllpets()
            .then(
            function(d) {
                self.pets = d;
            },
            function(errResponse){
                console.error('Error while fetching pets');
            }
        );
    }
 
    function createpet(pet){
        PetService.createpet(pet)
            .then(
            fetchAllpets,
            function(errResponse){
                console.error('Error while creating pet');
            }
        );
    }
 
    function updatepet(pet, id){
        PetService.updatepet(pet, id)
            .then(
            fetchAllpets,
            function(errResponse){
                console.error('Error while updating pet');
            }
        );
    }
 
    function deletepet(id){
        PetService.deletepet(id)
            .then(
            fetchAllpets,
            function(errResponse){
                console.error('Error while deleting pet');
            }
        );
    }
 
    function submit() {
        if(self.pet.id===null){
            console.log('Saving New pet', self.pet);
            createpet(self.pet);
        }else{
            updatepet(self.pet, self.pet.id);
            console.log('pet updated with id ', self.pet.id);
        }
        reset();
    }
 
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.pets.length; i++){
            if(self.pets[i].id === id) {
                self.pet = angular.copy(self.pets[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(self.pet.id === id) {//clean form if the pet to be deleted is shown there.
            reset();
        }
        deletepet(id);
    }
 
 
    function reset(){
        self.pet={id:null,name:'',status:''};
        $scope.petForm.$setPristine(); //reset Form
    }
 
}]);