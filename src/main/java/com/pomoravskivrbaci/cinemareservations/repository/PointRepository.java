package com.pomoravskivrbaci.cinemareservations.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.Points;

@Repository
public interface PointRepository extends PagingAndSortingRepository<Points,Long>{

	@Modifying
	@Transactional
	@Query("Update Points p set p.login=?1, p.adReserved=?2, p.seatReserved=?3, p.addedFriend=?4, p.gold=?5, p.silver=?6, p.bronze=?7 where p.id=1")
	void save(int login, int adReserved, int seatReserved, int addedFriend,int gold,int silver,int bronze);

}
