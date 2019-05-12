package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class HomePagePersonHourChart implements Serializable {

    private Long designAndPlanning;

    private Long passed;

    private Long remaining;

    public HomePagePersonHourChart(Long designAndPlanning,
                                   Long passed,
                                   Long remaining) {
        this.designAndPlanning = designAndPlanning;
        this.passed = passed;
        this.remaining = remaining;
    }

    public Long getDesignAndPlanning() {
        return designAndPlanning;
    }

    public void setDesignAndPlanning(Long designAndPlanning) {
        this.designAndPlanning = designAndPlanning;
    }

    public Long getPassed() {
        return passed;
    }

    public void setPassed(Long passed) {
        this.passed = passed;
    }

    public Long getRemaining() {
        return remaining;
    }

    public void setRemaining(Long remaining) {
        this.remaining = remaining;
    }
}
