package com.pomoravskivrbaci.cinemareservations.repository;

import com.pomoravskivrbaci.cinemareservations.model.InstitutionRating;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRatingRepository extends PagingAndSortingRepository<InstitutionRating, Long> {
}
