/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic._1_api;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.samples.petclinic._2_service.OwnerService;
import org.springframework.samples.petclinic._4_domain.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
class OwnerController {

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/owners/new")
	public String initCreationForm(Map<String, Object> model) {
		return ownerService.initOwner(model, false);
	}

	@PostMapping("/owners/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result) {
		return ownerService.saveOwner(owner, result);
	}

	@GetMapping("/owners/find")
	public String initFindForm(Map<String, Object> model) {
		return ownerService.initOwner(model, true);
	}

	@GetMapping("/owners")
	public String processFindForm(@RequestParam(defaultValue = "1") int page, Owner owner, BindingResult result,
			Model model) {
		return ownerService.findOwners(page, owner, result, model);
	}

	@GetMapping("/owners/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
		return ownerService.initUpdateOwner(ownerId, model);
	}

	@PostMapping("/owners/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
			@PathVariable("ownerId") int ownerId) {
		return ownerService.updateOwner(owner, ownerId, result);
	}

	/**
	 * Custom handler for displaying an owner.
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
		return ownerService.getOwnerView(ownerId);
	}

}
