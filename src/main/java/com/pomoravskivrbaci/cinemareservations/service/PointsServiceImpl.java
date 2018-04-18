package com.pomoravskivrbaci.cinemareservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pomoravskivrbaci.cinemareservations.model.Points;
import com.pomoravskivrbaci.cinemareservations.repository.PointRepository;

@Service
public class PointsServiceImpl implements PointsService{

	@Autowired
	private PointRepository pointsRepository;
	
	@Override
	public void save(Points points) {
		// TODO Auto-generated method stub
		 pointsRepository.save(points.getLogin(),points.getAdReserved(),points.getSeatReserved(),points.getAddedFriend());
	}

	@Override
	public Points getPointsById(long l) {
		// TODO Auto-generated method stub
		return pointsRepository.findOne(l);
	}

}
