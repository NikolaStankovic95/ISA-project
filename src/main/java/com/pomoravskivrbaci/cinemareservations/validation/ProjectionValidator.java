package com.pomoravskivrbaci.cinemareservations.validation;

import com.pomoravskivrbaci.cinemareservations.model.Period;
import com.pomoravskivrbaci.cinemareservations.model.Projection;

public class ProjectionValidator extends Validator {

    private Projection projection;

    public ProjectionValidator(Projection projection) {
        this.projection = projection;
    }

    @Override
    public boolean validate() {
        boolean isValid = true;
        if(projection.getName().isEmpty()) {
            isValid = false;
            addResult("Name is required.");
        }

        if(projection.getPeriods() == null || projection.getPeriods().isEmpty()) {
            isValid = false;
            addResult("Periods is required.");
        } else if (projection.getDuration() != null){
            for(Period period : projection.getPeriods()) {
                //getTime vraca vreme u milisekundama pa zato delim sa hiljadu, a onda sa 60 da dobijem broj minuta
                if ((period.getDateEnd().getTime()/1000 - period.getDate().getTime()/1000)/60 < projection.getDuration()) {
                    isValid = false;
                    addResult("One of given periods is too short for projection duration.");
                    break;
                }
            }
        }

        if(projection.getActors().isEmpty()) {
            isValid = false;
            addResult("Actors are required.");
        }

        if(projection.getDirectorName().isEmpty()) {
            isValid = false;
            addResult("Director name is required.");
        }

        if(projection.getHalls() == null || projection.getHalls().size() == 0) {
            isValid = false;
            addResult("Halls are required.");
        }

        if(projection.getPrice() == null || projection.getPrice() == 0) {
            isValid = false;
            addResult("Price must be greater than 0.");
        }

        if(projection.getGenre().isEmpty()) {
            isValid = false;
            addResult("Genre is required.");
        }

        if(projection.getDuration() == null || projection.getDuration() == 0) {
            isValid = false;
            addResult("Duration must be greater than zero.");
        }

        return isValid;
    }
}
