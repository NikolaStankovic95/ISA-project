package com.pomoravskivrbaci.cinemareservations.repository;


import java.util.List;

import org.springframework.data.repository.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Hall;

public interface HallRepository extends Repository<Hall,Long>{

	List<Hall> findByInstitutionId(Long id);
}
