package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.InstitutionType;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InstitutionRepository extends PagingAndSortingRepository<Institution,Long> {

	List<Institution> findByType(InstitutionType type);
	
	Institution findByName(String name);

	Institution findById(Long id);

	List<Institution> findAll();

	@Modifying
	@Transactional
	@Query("update Institution i set i.name = ?2, i.address = ?3, i.description = ?4 where i.id = ?1")
	void setInstitutionInfoById(Long id, String name, String address, String description);

}
