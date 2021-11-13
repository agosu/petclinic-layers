package org.springframework.samples.petclinic._1_api;

import org.springframework.samples.petclinic._2_service.VisitService;
import org.springframework.samples.petclinic._4_domain.Visit;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/visits")
class VisitController {

	private final VisitService visitService;

	public VisitController(VisitService visitService) {
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

	@GetMapping("/byPetId/{id}")
	public List<Visit> getVisitByPetId(@PathVariable int id) {
		return visitService.findByPetId(id);
	}

	@PostMapping
	public Visit createVisit(@Valid Visit visit) {
		return visitService.createVisit(visit);
	}

	@DeleteMapping("/{id}")
	public void deleteVisit(@PathVariable  int id){
		visitService.deleteVisitById(id);
	}

}
