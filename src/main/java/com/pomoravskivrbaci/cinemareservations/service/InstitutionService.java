package com.pomoravskivrbaci.cinemareservations.service;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Institution;
import com.pomoravskivrbaci.cinemareservations.model.InstitutionType;
import com.pomoravskivrbaci.cinemareservations.model.Repertoire;

public interface InstitutionService {

	List<Institution> findByType(InstitutionType type);

	Institution findByName(String name);

	Repertoire findByRepertoire(Long id);

	Institution findById(Long id);

	List<Institution> findAll();

	void setInstitutionInfoById(Long id, String name, String address, String description);

	Institution saveOrUpdate(Institution institution);
}
