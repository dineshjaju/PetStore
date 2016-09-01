package com.petstore.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petstore.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>
{

}
