package org.springframework.samples.petclinic._3_repository;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic._4_domain.Specialty;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SpecialtyRepository extends Repository<Specialty, Integer>  {

	@Transactional(readOnly = true)
	List<Specialty> findAll();

	@Transactional(readOnly = true)
	Specialty findById(@Param("id") Integer id);

	Specialty save(Specialty specialty);

	void deleteById(int id);

}
