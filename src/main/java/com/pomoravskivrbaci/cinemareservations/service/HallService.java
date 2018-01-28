package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Hall;

public interface HallService {

	List<Hall> findByInstitutionId(Long id);
}
