package com.pomoravskivrbaci.cinemareservations.repository;

import com.pomoravskivrbaci.cinemareservations.model.Repertoire;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface RepertoireRepository extends PagingAndSortingRepository<Repertoire,Long> {

    Repertoire findById(Long id);

    @Modifying
    @Transactional
    @Query("update Repertoire r set r.name = ?2 where r.id = ?1")
    void setRepertoireInfoById(Long id, String name);
}
