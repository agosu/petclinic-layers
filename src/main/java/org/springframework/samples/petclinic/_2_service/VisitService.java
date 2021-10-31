package org.springframework.samples.petclinic._2_service;

import org.springframework.samples.petclinic._3_repository.VisitRepository;
import org.springframework.samples.petclinic._4_domain.Visit;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VisitService {

	private final VisitRepository visitRepository;

	public VisitService(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	public List<Visit> getVisits() {
		return visitRepository.findAll();
	}

	public Visit findVisitById(int visitId) {
		return visitRepository.findById(visitId);
	}

	public Visit createVisit(Visit visit) {
		return visitRepository.save(visit);
	}

	public Visit updateVisit(Visit visit) {
		return visitRepository.update(visit);
	}

	public void deleteVisitById(int id) {
		visitRepository.deleteById(id);
	}

	public List<Visit> findByPetId(int petId) {
		return visitRepository.findByPetId(petId);
	}

}
