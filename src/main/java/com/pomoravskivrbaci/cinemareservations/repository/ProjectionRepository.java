package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import com.pomoravskivrbaci.cinemareservations.model.Period;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Projection;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProjectionRepository extends PagingAndSortingRepository<Projection,Long>{

	Projection findById(Long id);

	List<Projection> findByRepertoires_id(Long id);
	
	@Modifying
	@Transactional
	@Query("update Projection p set p.name = ?2, p.actors = ?3, p.genre = ?4, p.description = ?5, p.directorName = ?6, p.price = ?7 where p.id = ?1")
	void setProjectionInfoById(Long id, String name, String actors, String genre, String description, String directorName, Double price);

	Projection findByPeriods(Period period);

}
