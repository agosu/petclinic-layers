package org.springframework.samples.petclinic._2_service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic._3_repository.OwnerRepository;
import org.springframework.samples.petclinic._3_repository.VisitRepository;
import org.springframework.samples.petclinic._4_domain.Owner;
import org.springframework.samples.petclinic._4_domain.Pet;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Service
public class OwnerService {

	private final OwnerRepository owners;
	private final VisitRepository visits;

	public OwnerService(OwnerRepository ownerRepository, VisitRepository visits) {
		this.owners = ownerRepository;
		this.visits = visits;
	}

	public void createInitOwner(Map<String, Object> model) {
		Owner owner = new Owner();
		model.put("owner", owner);
	}

	public void saveOwner(Owner owner) {
		this.owners.save(owner);
	}

	public String findOwners(int page, Owner owner, BindingResult result, Model model) {
		if (owner.getLastName() == null) {
			owner.setLastName("");
		}

		String lastName = owner.getLastName();
		Page<Owner> ownersResults = findPaginatedForOwnersLastName(page, lastName);
		if (ownersResults.isEmpty()) {
			// no owners found
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		}
		else if (ownersResults.getTotalElements() == 1) {
			// 1 owner found
			owner = ownersResults.iterator().next();
			return "redirect:/owners/" + owner.getId();
		}
		else {
			// multiple owners found
			lastName = owner.getLastName();
			return addPaginationModel(page, model, lastName, ownersResults);
		}
	}

	public Owner findById(int ownerId) {
		return this.owners.findById(ownerId);
	}

	public String updateOwner(Owner owner, int ownerId) {
		owner.setId(ownerId);
		this.owners.save(owner);
		return "redirect:/owners/{ownerId}";
	}

	public ModelAndView getOwnerView(int ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Owner owner = this.owners.findById(ownerId);
		for (Pet pet : owner.getPets()) {
			pet.setVisitsInternal(visits.findByPetId(pet.getId()));
		}
		mav.addObject(owner);
		return mav;
	}

	private String addPaginationModel(int page, Model model, String lastName, Page<Owner> paginated) {
		model.addAttribute("listOwners", paginated);
		List<Owner> listOwners = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listOwners", listOwners);
		return "owners/ownersList";
	}

	private Page<Owner> findPaginatedForOwnersLastName(int page, String lastname) {

		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return owners.findByLastName(lastname, pageable);

	}

}
