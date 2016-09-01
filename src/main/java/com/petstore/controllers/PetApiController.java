package com.petstore.controllers;

import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVerb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.exception.PetNotFoundException;
import com.petstore.model.Pet;
import com.petstore.service.PetRepository;


@Api(name="Pet", description = "Everything about your Pets", stage = ApiStage.BETA)
@ApiVersion(since = "")
@RestController
public class PetApiController 
{
	@Autowired
	private PetRepository petRepository ;

	@RequestMapping(value = "/pet/{petId}" , method = RequestMethod.GET ,
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} ,
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}) 
	public ResponseEntity<Pet> findPetById(@ApiPathParam(description = "ID of pet to return") @PathVariable("petId") long id) throws Exception
	{		
		Pet pet = petRepository.findOne(id) ;

		if(pet == null)
		{
			throw new PetNotFoundException();
		}

		return new ResponseEntity<Pet>(pet, HttpStatus.OK);

	}

	@RequestMapping(value = "/pet/{petId}" , method = RequestMethod.DELETE ,
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Pet> removePetByid(@ApiPathParam(description = "Pet id to delete") @PathVariable("petId") long id) throws Exception
	{
		Pet pet = petRepository.findOne(id) ;

		if(pet == null)
		{
			throw new PetNotFoundException();
		}

		petRepository.delete(id);

		return new ResponseEntity<Pet>(HttpStatus.NO_CONTENT);
	}


	@ApiMethod(description ="Add a new pet to the store" , path = "/pet" , verb = ApiVerb.POST)
	@RequestMapping(value = "/pet" , method = RequestMethod.POST ,
	produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} , 
	consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Pet> addPet(@RequestBody Pet pet)
	{
		Pet sPet = petRepository.save(pet);

		return new ResponseEntity<Pet>(sPet, HttpStatus.OK);
	}

	@RequestMapping(value = "/pet/", method = RequestMethod.GET)
	//produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} ,
	//consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}) 
	public ResponseEntity<List<Pet>> findAllPets()
	{
		List<Pet> pets =  petRepository.findAll();
		if(pets.isEmpty()){
			return new ResponseEntity<List<Pet>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Pet>>(pets, HttpStatus.OK);
	}

	@RequestMapping(value = "/pet/{petId}" , method = RequestMethod.PUT ,
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} ,
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}) 
	public ResponseEntity<Pet> updatePetById(@ApiPathParam(description = "ID of pet to return") @PathVariable("petId") long id , @RequestBody Pet pet) throws Exception
	{		
		Pet fromDB = petRepository.findOne(id) ;

		if(fromDB == null)
		{
			throw new PetNotFoundException();
		}
		
		fromDB.setName(pet.getName());
		fromDB.setStatus(pet.getStatus());
		
		petRepository.save(fromDB);
		
		return new ResponseEntity<Pet>(pet, HttpStatus.OK);

	}


}
