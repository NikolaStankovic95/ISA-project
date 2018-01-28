package com.pomoravskivrbaci.cinemareservations.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pomoravskivrbaci.cinemareservations.model.User;


@Repository
public interface userRepository extends PagingAndSortingRepository<User,Long> {

	@Query("select r from User r where r.email = ?1 and r.password = ?2")
    User findUserByEmailAndPassword(String email, String password);
   
	@Query("select r from User r where r.id = ?1")
    User findUserById(Long id);
	
	@Modifying
	@Transactional
	@Query("update User u set u.activated = ?1 where u.id = ?2")
	int setFixedActivatedFor(boolean activated,Long id);

	
	
	User save(User user);
}
