package com.pomoravskivrbaci.cinemareservations.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import com.pomoravskivrbaci.cinemareservations.model.User;

public interface FriendshipRepository extends PagingAndSortingRepository<Friendship,Long> {

	
	Friendship findByUserAndFriend(User user,User friend);
	
	@Modifying
	@Transactional
	@Query("delete from Friendship f where f.id = ?1")
	void deleteById(Long id);
}
