package com.pomoravskivrbaci.cinemareservations.validation;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.HallSegment;

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
            addResult("Hall segments are required.");
        } else {
            for(HallSegment hallSegment : hall.getHallSegments()) {
                if (hallSegment.getNumberOfColumns() == 0 || hallSegment.getNumberOfRows() == 0) {
                    isValid = false;
                    addResult("Hall segment number of rows/columns cannot be zero.");
                    break;
                }
            }
        }

        return isValid;
    }
}
