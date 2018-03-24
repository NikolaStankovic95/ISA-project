package com.pomoravskivrbaci.cinemareservations.service;

import com.pomoravskivrbaci.cinemareservations.model.Repertoire;

public interface RepertoireService {

    Repertoire findById(Long id);

    void setRepertoireInfoById(Long id, String name);

}
