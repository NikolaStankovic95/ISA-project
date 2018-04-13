package com.pomoravskivrbaci.cinemareservations.service;

import com.pomoravskivrbaci.cinemareservations.model.ProjectionRating;
import com.pomoravskivrbaci.cinemareservations.repository.ProjectionRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectionRatingServiceImpl implements ProjectionRatingService {

    @Autowired
    ProjectionRatingRepository projectionRatingRepository;

    @Override
    public ProjectionRating saveOrUpdate(ProjectionRating projectionRating) {
        return projectionRatingRepository.save(projectionRating);
    }
}
