package com.pomoravskivrbaci.cinemareservations.validation;

public abstract class Validator {
    protected String results = "";

    public abstract boolean validate();
    public String getResults() {
        return results;
    }
    protected void addResult(String error) {
        results += error + "\n";
    }
}
