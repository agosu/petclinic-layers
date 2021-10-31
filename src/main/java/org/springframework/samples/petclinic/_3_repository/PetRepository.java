package org.springframework.samples.petclinic._3_repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic._4_domain.Pet;
import org.springframework.samples.petclinic._4_domain.PetType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PetRepository extends Repository<Pet, Integer> {

	@Query("SELECT pet FROM Pet pet")
	@Transactional(readOnly = true)
	List<Pet> findAll();

	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
	@Transactional(readOnly = true)
	List<PetType> findPetTypes();

	@Transactional(readOnly = true)
	Pet findById(Integer id);

	Pet save(Pet pet);

	Pet update(Pet pet);

	void deleteById(int id);

}
