package org.springframework.samples.petclinic._1_api;

import org.springframework.samples.petclinic._2_service.VisitService;
import org.springframework.samples.petclinic._3_repository.PetRepository;
import org.springframework.samples.petclinic._4_domain.Visit;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController("/visits")
class VisitController {

	private final VisitService visitService;

	public VisitController(VisitService visitService, PetRepository pets) {
		this.visitService = visitService;
	}

	@GetMapping
	public List<Visit> getVisits() {
		return visitService.getVisits();
	}

	@GetMapping("/{id}")
	public Visit getVisit(@PathVariable int id) {
		return visitService.findVisitById(id);
	}

	@PostMapping
	public Visit createVisit(@Valid Visit visit) {
		return visitService.createVisit(visit);
	}

	@PutMapping
	public Visit updateVisit(@Valid Visit visit) {
		return visitService.updateVisit(visit);
	}

	@DeleteMapping("/{id}")
	public void deleteVisit(@PathVariable  int id){
		visitService.deleteVisitById(id);
	}

}
