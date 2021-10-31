package org.springframework.samples.petclinic._3_repository;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic._4_domain.Visit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VisitRepository extends Repository<Visit, Integer> {

	@Transactional(readOnly = true)
	List<Visit> findAll();

	@Transactional(readOnly = true)
	Visit findById(@Param("id") Integer id);

	Visit save(Visit visit);

	void deleteById(int id);

	List<Visit> findByPetId(Integer petId);

}
