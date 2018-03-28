package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pomoravskivrbaci.cinemareservations.model.HallSegment;


public interface HallSegmentRepository extends PagingAndSortingRepository<HallSegment,Long>{

	List<HallSegment> findByHallId(Long ig);
}
