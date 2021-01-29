package com.marineindustryproj.service.util;

import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.RangeFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

public class QueryServiceNew<ENTITY> extends QueryService<ENTITY> {
    protected <X extends Comparable<? super X>> Specification<ENTITY> buildSpecification(LongFilterEx<X> filter, Function<Root<ENTITY>, Expression<X>> metaclassFunction) {
        if (filter.getEquals() != null) {
            return this.equalsSpecification(metaclassFunction, filter.getEquals());
        } else if (filter.getIn() != null) {
            return this.valueIn((Function)metaclassFunction, filter.getIn());
        } else if (filter.getIn() != null) {
            return this.valueIn((Function)metaclassFunction, filter.getNotIn());
        }else {
            Specification<ENTITY> result = Specification.where((Specification)null);
            if (filter.getSpecified() != null) {
                result = result.and(this.byFieldSpecified(metaclassFunction, filter.getSpecified()));
            }

            if (filter.getGreaterThan() != null) {
                result = result.and(this.greaterThan(metaclassFunction, filter.getGreaterThan()));
            }

            if (filter.getGreaterOrEqualThan() != null) {
                result = result.and(this.greaterThanOrEqualTo(metaclassFunction, filter.getGreaterOrEqualThan()));
            }

            if (filter.getLessThan() != null) {
                result = result.and(this.lessThan(metaclassFunction, filter.getLessThan()));
            }

            if (filter.getLessOrEqualThan() != null) {
                result = result.and(this.lessThanOrEqualTo(metaclassFunction, filter.getLessOrEqualThan()));
            }

            return result;
        }
    }
}
