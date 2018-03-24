package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Projection;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProjectionRepository extends PagingAndSortingRepository<Projection,Long>{

	public Projection findById(Long id);

	public List<Projection> findByRepertoires_id(Long id);

	@Modifying
	@Transactional
	@Query("update Projection p set p.name = ?2, p.actors = ?3, p.genre = ?4, p.description = ?5, p.directorName = ?6, p.rating = ?7, p.price = ?8 where p.id = ?1")
	void setProjectionInfoById(Long id, String name, String actors, String genre, String description, String directorName, double rating, Double price);
	
}
