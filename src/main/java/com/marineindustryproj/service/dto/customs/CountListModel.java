package com.marineindustryproj.service.dto.customs;

import java.util.List;

public class CountListModel {

    public CountListModel(Long entityId, Long count) {
        this.entityId = entityId;
        this.count = count;
    }

    private Long entityId;
    private Long count;

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
