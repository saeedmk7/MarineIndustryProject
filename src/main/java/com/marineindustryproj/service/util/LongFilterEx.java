package com.marineindustryproj.service.util;

import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.RangeFilter;

import java.util.List;

public class LongFilterEx<FIELD_TYPE extends Comparable<? super FIELD_TYPE>> extends RangeFilter<FIELD_TYPE> {
    private static final long serialVersionUID = 1L;

    public LongFilterEx() {
    }

    private List<FIELD_TYPE> notIn;

    public List<FIELD_TYPE> getNotIn() {
        return notIn;
    }

    public LongFilterEx<FIELD_TYPE> setNotIn(List<FIELD_TYPE> notIn) {
        this.notIn = notIn;
        return this;
    }
}
