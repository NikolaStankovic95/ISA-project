package com.pomoravskivrbaci.cinemareservations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.Friendship;
import com.pomoravskivrbaci.cinemareservations.model.User;


@Repository
public interface userRepository extends PagingAndSortingRepository<User,Long> {

	
	List<User> findAll();
	
	@Query("select r from User r where r.email = ?1 and r.password = ?2")
    User findUserByEmailAndPassword(String email, String password);
   
	@Query("select r from User r where r.email = ?1")
    User findUserByEmail(String email);
   
	@Query("select r from User r where r.id = ?1")
    User findUserById(Long id);
	
	@Modifying
	@Transactional
	@Query("update User u set u.activated = ?1 where u.id = ?2")
	int setFixedActivatedFor(boolean activated,Long id);
	
	@Query("Select  f FROM User AS u LEFT JOIN u.friendships AS f WHERE f.friend = ?1 and f.accepted=?2")
	List<Friendship> findFriends(User id,boolean accepted);
	
	@Query("Select  f FROM User AS u LEFT JOIN u.friendships AS f WHERE f.user = ?1 and f.accepted=?2")
	List<Friendship> findFriendRequests(User id,boolean accepted);
}
