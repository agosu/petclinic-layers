package org.springframework.samples.petclinic._1_api;

import java.util.List;
import javax.validation.Valid;
import org.springframework.samples.petclinic._2_service.OwnerService;
import org.springframework.samples.petclinic._4_domain.Owner;
import org.springframework.web.bind.annotation.*;

@RestController("/owners")
class OwnerController {

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@GetMapping
	public List<Owner> getOwners() {
		return ownerService.getOwners();
	}

	@GetMapping("/{id}")
	public Owner getOwner(@PathVariable int id) {
		return ownerService.findOwnerById(id);
	}

	@PostMapping
	public Owner createOwner(@Valid Owner owner) {
		return ownerService.createOwner(owner);
	}

	@PutMapping
	public Owner updateOwner(@Valid Owner owner) {
		return ownerService.updateOwner(owner);
	}

	@DeleteMapping("/{id}")
	public void deleteOwner(@PathVariable  int id){
		ownerService.deleteOwnerById(id);
	}

}
