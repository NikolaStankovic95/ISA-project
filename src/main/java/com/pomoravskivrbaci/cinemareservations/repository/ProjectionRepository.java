package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Projection;
@Repository
public interface ProjectionRepository extends PagingAndSortingRepository<Projection,Long>{

	public List<Projection> findByRepertoires_id(Long id);
	
}
