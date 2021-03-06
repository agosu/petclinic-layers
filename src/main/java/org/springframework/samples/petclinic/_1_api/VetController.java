package org.springframework.samples.petclinic._1_api;

import org.springframework.samples.petclinic._2_service.VetService;
import org.springframework.samples.petclinic._4_domain.Specialty;
import org.springframework.samples.petclinic._4_domain.Vet;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vets")
class VetController {

	private final VetService vetService;

	public VetController(VetService vetService) {
		this.vetService = vetService;
	}

	@GetMapping
	public List<Vet> getVets() {
		return vetService.getVets();
	}

	@GetMapping("/{id}")
	public Vet getVet(@PathVariable int id) {
		return vetService.findVetById(id);
	}

	@PostMapping
	public Vet createOwner(@Valid Vet vet) {
		return vetService.createVet(vet);
	}

	@DeleteMapping("/{id}")
	public void deleteVet(@PathVariable  int id){
		vetService.deleteVetById(id);
	}

	@PutMapping("/{vetId}/specialties/{specialtyId}")
	public Vet addSpecialtyToVet(@PathVariable int vetId, @PathVariable int specialtyId) {
		return vetService.addSpecialty(vetId, specialtyId);
	}

	@PostMapping("/specialties")
	public Specialty createSpecialty(@Valid Specialty specialty){
		return vetService.createSpecialty(specialty);
	}

}
