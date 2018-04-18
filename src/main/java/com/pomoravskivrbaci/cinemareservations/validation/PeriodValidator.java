package com.pomoravskivrbaci.cinemareservations.validation;

import com.pomoravskivrbaci.cinemareservations.model.Hall;
import com.pomoravskivrbaci.cinemareservations.model.Period;
import java.util.Date;

public class PeriodValidator extends Validator {

    public Period period;

    public PeriodValidator(Period period) {
        this.period = period;
    }

    @Override
    public boolean validate() {
        boolean isValid = true;

        if(period.getDate() == null || period.getDateEnd() == null) {
            addResult("Period start and end date cannot be empty.");
            return false;
        }

        if(period.getDate().before(new Date())) {
            addResult("Period start must be in the future.");
            return false;
        }

        if(period.getDate().equals(period.getDateEnd())) {
            addResult("Period start and end cannot be the same.");
            return false;
        }

        if(period.getDate().after(period.getDateEnd())) {
            isValid = false;
            addResult("Start date of period must be before end date.");
        }

        for(Hall hall : period.getHalls()) {
            for(Period periodsInHall : hall.getPeriods()) {
                if((period.getDate().equals(periodsInHall.getDate()) && period.getDateEnd().equals(periodsInHall.getDateEnd()))
                        ||(period.getDate().after(periodsInHall.getDate()) && period.getDate().before(periodsInHall.getDateEnd()))
                        || (period.getDateEnd().after(periodsInHall.getDate()) && period.getDateEnd().before(periodsInHall.getDateEnd()))) {
                    isValid = false;
                    addResult("Period time interferes with another period in this hall.");
                    break;
                }
            }
            if(!isValid) {
                break;
            }
        }

        return isValid;
    }
}
