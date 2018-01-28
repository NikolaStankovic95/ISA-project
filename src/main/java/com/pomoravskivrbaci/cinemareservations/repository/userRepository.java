package com.pomoravskivrbaci.cinemareservations.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.User;


@Repository
public interface userRepository extends PagingAndSortingRepository<User,String> {

	@Query("select r from User r where r.email = ?1 and r.password = ?2")
    User findUserByEmailAndPassword(String email, String password);
   
	@Query("select r from User r where r.email = ?1")
    User findUserByEmail(String email);
	
	@Modifying
	@Transactional
	@Query("update User u set u.activated = ?1 where u.email = ?2")
	int setFixedActivatedFor(boolean activated,String email);

	
	
	User save(User user);
}
