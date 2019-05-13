package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class HomePagePersonHourChart implements Serializable {

    private Float designAndPlanning;

    private Float passed;

    private Float remaining;

    public HomePagePersonHourChart(Float designAndPlanning, Float passed, Float remaining) {
        this.designAndPlanning = designAndPlanning;
        this.passed = passed;
        this.remaining = remaining;
    }

    public HomePagePersonHourChart() {

    }


    public Float getDesignAndPlanning() {
        return designAndPlanning;
    }

    public void setDesignAndPlanning(Float designAndPlanning) {
        this.designAndPlanning = designAndPlanning;
    }

    public Float getPassed() {
        return passed;
    }

    public void setPassed(Float passed) {
        this.passed = passed;
    }

    public Float getRemaining() {
        return remaining;
    }

    public void setRemaining(Float remaining) {
        this.remaining = remaining;
    }
}
