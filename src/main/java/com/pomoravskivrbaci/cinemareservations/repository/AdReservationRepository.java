package com.pomoravskivrbaci.cinemareservations.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.OfficalAdReservation;
import com.pomoravskivrbaci.cinemareservations.model.User;

@Repository
public interface AdReservationRepository extends
		PagingAndSortingRepository<OfficalAdReservation, Long> {
	@Modifying
	@Query(value = "insert into offical_ad_reservation(reserved_ad_id,user_id)  VALUES (:ad,:user)", nativeQuery = true)
	@Transactional
	void insertOfficialAd(@Param("ad") Ad ad, @Param("user") User user);
	@Modifying
	@Query(value = "insert into unofficial_ad_reservation(bid,reserved_ad_id,user_id)  VALUES (:bid,:ad,:user)", nativeQuery = true)
	@Transactional
	void insertUnofficialAd(@Param("bid")int bid,@Param("ad") Ad ad, @Param("user") User user);

	

}
