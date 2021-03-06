package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.HallSegment;
import com.pomoravskivrbaci.cinemareservations.repository.HallSegmentRepository;

@Service
public class HallSegmentServiceImpl implements HallSegmentService{

	@Autowired
	private HallSegmentRepository hallSegmentRepository;
	
	@Override
	public List<HallSegment> findHallSegmentByHallId(Long id,boolean closed) {
		// TODO Auto-generated method stub
		return hallSegmentRepository.findByHallIdAndClosed(id,closed);
	}

	@Override
	public HallSegment saveOrUpdate(HallSegment hallSegment) {
		return hallSegmentRepository.save(hallSegment);
	}

	@Override
	public HallSegment findById(Long id) {
		// TODO Auto-generated method stub
		return hallSegmentRepository.findOne(id);
	}

}
