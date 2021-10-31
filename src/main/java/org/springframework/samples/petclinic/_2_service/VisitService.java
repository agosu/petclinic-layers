package org.springframework.samples.petclinic._2_service;

import org.springframework.samples.petclinic._3_repository.PetRepository;
import org.springframework.samples.petclinic._3_repository.VisitRepository;
import org.springframework.samples.petclinic._4_domain.Pet;
import org.springframework.samples.petclinic._4_domain.Visit;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Map;

@Service
public class VisitService {

	private final VisitRepository visits;
	private final PetService petService;

	public VisitService(VisitRepository visitRepository, PetService petService) {
		this.visits = visitRepository;
		this.petService = petService;
	}

	public Visit getVisit(int petId, Map<String, Object> model) {
		Pet pet = petService.findById(petId);
		pet.setVisitsInternal(this.visits.findByPetId(petId));
		model.put("pet", pet);
		Visit visit = new Visit();
		pet.addVisit(visit);
		return visit;
	}

	public String createNewVisit(Visit visit, BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}
		else {
			this.visits.save(visit);
			return "redirect:/owners/{ownerId}";
		}
	}

}
