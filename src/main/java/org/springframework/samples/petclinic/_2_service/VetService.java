package org.springframework.samples.petclinic._2_service;

import org.springframework.samples.petclinic._3_repository.SpecialtyRepository;
import org.springframework.samples.petclinic._3_repository.VetRepository;
import org.springframework.samples.petclinic._4_domain.Specialty;
import org.springframework.samples.petclinic._4_domain.Vet;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class VetService {

	private final VetRepository vetRepository;

	private final SpecialtyRepository specialtyRepository;

	public VetService(VetRepository vetRepository, SpecialtyRepository specialtyRepository) {
		this.vetRepository = vetRepository;
		this.specialtyRepository = specialtyRepository;
	}

	public List<Vet> getVets() {
		return vetRepository.findAll();
	}

	public Vet findVetById(int vetId) {
		return vetRepository.findById(vetId);
	}

	public Vet createVet(Vet vet) {
		return vetRepository.save(vet);
	}

	public void deleteVetById(int id) {
		vetRepository.deleteById(id);
	}

	public Vet addSpecialty(int vetId, int specialtyId) {
		Vet vet = vetRepository.findById(vetId);
		Specialty specialty = specialtyRepository.findById(specialtyId);
		vet.getSpecialties().add(specialty);
		return vet;
	}

	public Specialty createSpecialty(@Valid Specialty specialty) {
		return specialtyRepository.save(specialty);
	}

}
