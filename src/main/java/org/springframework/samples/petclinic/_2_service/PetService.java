package org.springframework.samples.petclinic._2_service;

import org.springframework.samples.petclinic._3_repository.PetRepository;
import org.springframework.samples.petclinic._4_domain.Pet;
import org.springframework.samples.petclinic._4_domain.PetType;
import org.springframework.samples.petclinic._4_domain.Visit;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PetService {

	private final PetRepository petRepository;
	private final VisitService visitService;

	public PetService(PetRepository petRepository, VisitService visitService) {
		this.petRepository = petRepository;
		this.visitService = visitService;
	}

	public List<PetType> getPetTypes() {
		return petRepository.findPetTypes();
	}

	public List<Pet> getPets() {
		return petRepository.findAll();
	}

	public Pet findPetById(int id) {
		return petRepository.findById(id);
	}

	public Pet createPet(Pet pet) {
		return petRepository.save(pet);
	}

	public void deletePetById(int id) {
		petRepository.deleteById(id);
	}

	public Pet addVisit(int id, Visit visit) {
		Pet pet = petRepository.findById(id);
		Visit newVisit = visitService.createVisit(visit);
		pet.getVisits().add(newVisit);
		return pet;
	}

}
