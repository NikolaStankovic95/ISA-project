package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Period;
@Repository
public interface PeriodRepository extends PagingAndSortingRepository<Period,Long>{

	public List<Period> findByProjectionId(Long id);
	public List<Period> findByHalls_Id(Long id);
	
	public Period findById(Long id);
	List<Period> findByHalls_IdAndProjectionId(Long parseLong, Long projectionId);

}
