package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.HallSegment;


public interface HallSegmentRepository extends PagingAndSortingRepository<HallSegment,Long>{
	
	
	@Transactional
	List<HallSegment> findByHallIdAndClosed(Long ig,boolean closed);
}
