package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.AdStatus;
import com.pomoravskivrbaci.cinemareservations.model.FanZone;

@Repository
public interface AdRepository extends PagingAndSortingRepository<Ad,Long> {

	@Query("select r from Ad r where r.fanZone = ?1 and r.adStatus = 0" )
    List<Ad> getAdByFanZoneId(FanZone id);
	
	@Query("select r from Ad r where r.adStatus = 2" )
    List<Ad> getInitAds();
	
	
	Ad save(Ad ad);
	
	@Modifying
	@Query("update Ad a set a.adStatus = ?1 where a.id = ?2")
	void update(AdStatus status,Long id);
}
