package com.pomoravskivrbaci.cinemareservations.validation;

import com.pomoravskivrbaci.cinemareservations.model.Hall;

public class HallValidator extends Validator{

    private Hall hall;

    public HallValidator(Hall hall) {
        this.hall = hall;
    }

    @Override
    public boolean validate() {
        boolean isValid = true;
        if(hall.getName().isEmpty()) {
            isValid = false;
            addResult("Name is required.");
        }
        if(hall.getHallSegments() == null || hall.getHallSegments().isEmpty()) {
            isValid = false;
            addResult("Hall segments is required.");
        }

        return isValid;
    }
}
