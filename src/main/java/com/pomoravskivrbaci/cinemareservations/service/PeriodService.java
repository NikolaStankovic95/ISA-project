package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Period;

public interface PeriodService {

	List<Period> findByProjectionId(Long id);
	Period findById(Long id);
	Period saveOrUpdate(Period period);

}
