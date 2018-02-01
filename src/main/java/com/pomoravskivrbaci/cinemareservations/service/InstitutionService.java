package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.InstitutionType;

public interface InstitutionService {

	List<Institution> findByType(InstitutionType type);

	Institution findByName(String name);

	Institution findById(Long id);

	List<Institution> findAll();
}
