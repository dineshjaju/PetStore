package com.petstore.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petstore.model.Category;
import com.petstore.model.Pet;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{

}
