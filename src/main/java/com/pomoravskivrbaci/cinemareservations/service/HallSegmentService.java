package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.HallSegment;

public interface HallSegmentService {

	List<HallSegment> findHallSegmentByHallId(Long id,boolean closed);

	HallSegment saveOrUpdate(HallSegment hallSegment);
	
	HallSegment findById(Long id);
}
