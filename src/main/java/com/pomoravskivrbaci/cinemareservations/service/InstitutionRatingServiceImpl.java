package com.pomoravskivrbaci.cinemareservations.service;

import com.pomoravskivrbaci.cinemareservations.model.InstitutionRating;
import com.pomoravskivrbaci.cinemareservations.repository.InstitutionRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionRatingServiceImpl implements InstitutionRatingService{

    @Autowired
    InstitutionRatingRepository institutionRatingRepository;

    @Override
    public InstitutionRating saveOrUpdate(InstitutionRating institutionRating) {
        return institutionRatingRepository.save(institutionRating);
    }

}
