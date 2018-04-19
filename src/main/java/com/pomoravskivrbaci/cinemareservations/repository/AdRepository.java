package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.AdStatus;
import com.pomoravskivrbaci.cinemareservations.model.FanZone;
import com.pomoravskivrbaci.cinemareservations.model.User;

@Repository
public interface AdRepository extends PagingAndSortingRepository<Ad,Long> {

	@Query("select r from Ad r where r.fanZone = ?1 and r.adStatus = 0" )
    List<Ad> getAdByFanZoneId(FanZone id);
	
	@Query("select r from Ad r, FanZone fz where r.fanZone = fz.id and r.adStatus = 2 and r.adType = 1 and ?1 MEMBER OF fz.fanZoneAdmins" )
    List<Ad> getInitAds(User user);
	
	
	@Modifying
	@Query("update Ad a set a.name = ?1, a.description= ?2, a.quantity = ?3 where a.id = ?4")
	void updateAd(String name, String description, int quantity,Long id);
	
	@Modifying
	@Query("update Ad a set a.adStatus = ?1, a.version=a.version+1 where a.id = ?2")
	void update(AdStatus status,Long id);
	@Query("select r from Ad r where r.fanZone = ?1 and r.adType = 0 and r.quantity > 0" )
	List<Ad> getOfficialAds(FanZone fz);
	
	@Query("select r from Ad r where r.fanZone = ?1 and r.adType = 1 and r.adStatus = 0 and r.expirationDate>=CURDATE()" )
	List<Ad> getUnofficialAds(FanZone fz);
	@Modifying
	@Query("update Ad a set a.quantity = ?1 where a.id = ?2")
	void updateQuantity(int i, Long id);
}
