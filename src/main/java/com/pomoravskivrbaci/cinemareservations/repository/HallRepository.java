package com.pomoravskivrbaci.cinemareservations.repository;


import java.util.List;

import org.springframework.data.repository.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Institution;

public interface HallRepository extends Repository<Hall,Long>{

	List<Hall> findByInstitutionId(Long id);
	List<Hall> findByProjections_id(Long id);
}
