package com.marineindustryproj.service.dto.customs;

import java.util.List;

public class FinalNiazsanjiPeopleListModel {

    public FinalNiazsanjiPeopleListModel(Long entityId, List<String> peopleFullNames) {
        this.entityId = entityId;
        this.peopleFullNames = peopleFullNames;
    }

    private Long entityId;
    private List<String> peopleFullNames;

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public List<String> getPeopleFullNames() {
        return peopleFullNames;
    }

    public void setPeopleFullNames(List<String> peopleFullNames) {
        this.peopleFullNames = peopleFullNames;
    }
}
