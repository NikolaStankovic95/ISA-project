package com.pomoravskivrbaci.cinemareservations.service;

import com.pomoravskivrbaci.cinemareservations.model.Repertoire;
import com.pomoravskivrbaci.cinemareservations.repository.RepertoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepertoireServiceImpl implements RepertoireService {

    @Autowired
    private RepertoireRepository repertoireRepository;

    @Override
    public Repertoire findById(Long id) {
        return repertoireRepository.findById(id);
    }

    @Override
    public void setRepertoireInfoById(Long id, String name) {
        repertoireRepository.setRepertoireInfoById(id, name);
    }

}
