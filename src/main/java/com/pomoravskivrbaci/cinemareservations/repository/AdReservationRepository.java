package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.Ad;
import com.pomoravskivrbaci.cinemareservations.model.OfficalAdReservation;
import com.pomoravskivrbaci.cinemareservations.model.UnofficialAdReservation;
import com.pomoravskivrbaci.cinemareservations.model.User;

@Repository
public interface AdReservationRepository extends
		PagingAndSortingRepository<OfficalAdReservation, Long> {
	@Modifying
	@Query(value = "insert into offical_ad_reservation(reserved_ad_id,user_id)  VALUES (:ad,:user)", nativeQuery = true)
	@Transactional
	void insertOfficialAd(@Param("ad") Ad ad, @Param("user") User user);

	@Modifying
	@Query(value = "insert into unofficial_ad_reservation(bid,reserved_ad_id,user_id,status)  VALUES (:bid,:ad,:user,:status)", nativeQuery = true)
	@Transactional
	void insertUnofficialAd(@Param("bid") int bid, @Param("ad") Ad ad,
			@Param("user") User user, @Param("status") int i);

	@Query("select u from unofficial_ad_reservation u, Ad a where u.reservedAd = a.id and a.publisher=?1 and u.status=0")
	List<UnofficialAdReservation> getMyAds(User user);

	@Modifying
	@Query(value = "update unofficial_ad_reservation  u set u.status=1  where u.reservedAd=?1")
	@Transactional
	void AcceptAd(Ad foundedAd);

	@Query("Select u from User u, unofficial_ad_reservation uaf where u.id = uaf.user and uaf.user !=?2 and uaf.reservedAd=?1 ")
	List<User> getRejectedUsers(Ad foundedAd, User user);
	
	@Query("select a, u.bid from unofficial_ad_reservation u, Ad a where u.reservedAd = a.id and u.user=?1 and u.status=0")
	List<Object> getBidedAds(User loggedUser);
	@Modifying
	@Query(value = "update unofficial_ad_reservation  u set u.bid=?1  where u.reservedAd=?2 and u.user=?3")
	@Transactional
	void changeBid(int bid, Ad foundedAd, User loggedUser);
	@Query("select a from unofficial_ad_reservation u, Ad a where u.reservedAd = a.id and u.user=?1")
	List<Ad> getMyBidedAds(User loggedUser);

}
