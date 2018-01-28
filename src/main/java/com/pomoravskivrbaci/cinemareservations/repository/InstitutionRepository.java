package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.InstitutionType;

@Repository
public interface InstitutionRepository extends PagingAndSortingRepository<Institution,Long> {

	List<Institution> findByType(InstitutionType type);
}
