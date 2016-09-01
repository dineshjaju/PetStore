package com.petstore.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.petstore.model.Category;
import com.petstore.model.Pet;
import com.petstore.model.Tag;
import com.petstore.utils.StatusEnum;

/**
 * Database seeder
 * 
 * @author dinesh.jaju
 *
 */
@Component
public class DatabaseSeeder implements CommandLineRunner
{

	@Autowired
	private PetRepository petRepository ;
	
	@Autowired
	private CategoryRepository catRepository ;
	
	@Override
	public void run(String... arg0) throws Exception 
	{
		List<Pet> listOfPets = new ArrayList<Pet>();


		listOfPets.add(new Pet(new Category("CAT"), "Doggie", new ArrayList<String>(Arrays.asList("A" , "B")), new ArrayList<Tag>() {{add(new Tag("Tag1"));add(new Tag("Tag2"));}}, StatusEnum.available )) ;

		listOfPets.add(new Pet(new Category("DOG"), "Maggie", new ArrayList<String>(Arrays.asList("C","D")), new ArrayList<Tag>() {{add(new Tag("Tag3"));}}, StatusEnum.sold )) ;

		listOfPets.add(new Pet(new Category("SNAKE"), "Hero", new ArrayList<String>(Arrays.asList("E","F")), new ArrayList<Tag>() {{add(new Tag("Tag4"));}}, StatusEnum.pending)) ;
		
		listOfPets.add(new Pet(new Category("CAT"), "Hero", new ArrayList<String>(Arrays.asList("G","H")), new ArrayList<Tag>() {{add(new Tag("Tag5"));}}, StatusEnum.pending)) ;
		
		petRepository.save(listOfPets);

	}

}
