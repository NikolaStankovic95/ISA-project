package com.pomoravskivrbaci.cinemareservations.service;

import com.pomoravskivrbaci.cinemareservations.model.Points;

public interface PointsService {
	void save(Points points);

	Points getPointsById(long l);
}
