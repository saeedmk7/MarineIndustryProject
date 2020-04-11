package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;

public class EffectivenessPhasePerCriteriaData  {

    private Object criteria;

    private Long sumValue;

    public Long getSumValue() {
        return sumValue;
    }

    public void setSumValue(Long sumValue) {
        this.sumValue = sumValue;
    }

    public Object getCriteria() {
        return criteria;
    }

    public void setCriteria(Object criteria) {
        this.criteria = criteria;
    }
}
