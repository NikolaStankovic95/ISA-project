package com.pomoravskivrbaci.cinemareservations.repository;

import com.pomoravskivrbaci.cinemareservations.model.ProjectionRating;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectionRatingRepository extends PagingAndSortingRepository<ProjectionRating, Long> {
}
