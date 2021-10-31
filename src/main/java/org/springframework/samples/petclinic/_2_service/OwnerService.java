package org.springframework.samples.petclinic._2_service;

import org.springframework.samples.petclinic._3_repository.OwnerRepository;
import org.springframework.samples.petclinic._4_domain.Owner;
import org.springframework.samples.petclinic._4_domain.Pet;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OwnerService {

	private final OwnerRepository ownerRepository;
	private final PetService petService;

	public OwnerService(OwnerRepository ownerRepository, PetService petService) {
		this.ownerRepository = ownerRepository;
		this.petService = petService;
	}

	public List<Owner> getOwners() {
		return ownerRepository.findAll();
	}

	public Owner findOwnerById(int ownerId) {
		return ownerRepository.findById(ownerId);
	}

	public Owner createOwner(Owner owner) {
		return ownerRepository.save(owner);
	}

	public void deleteOwnerById(int id) {
		ownerRepository.deleteById(id);
	}

	public Owner addPet(int id, Pet pet) {
		Pet newPet = petService.createPet(pet);
		Owner owner = ownerRepository.findById(id);
		owner.getPets().add(newPet);
		return owner;
	}

}
