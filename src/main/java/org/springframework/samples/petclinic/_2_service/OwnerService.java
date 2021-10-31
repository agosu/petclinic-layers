package org.springframework.samples.petclinic._2_service;

import org.springframework.samples.petclinic._3_repository.OwnerRepository;
import org.springframework.samples.petclinic._4_domain.Owner;
import org.springframework.samples.petclinic._4_domain.Pet;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OwnerService {

	private final OwnerRepository ownerRepository;

	public OwnerService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
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

	public void addPet(Owner owner, Pet pet) {
		owner.getPets().add(pet);
	}

}
