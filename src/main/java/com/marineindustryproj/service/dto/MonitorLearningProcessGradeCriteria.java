package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the MonitorLearningProcessGrade entity. This class is used in MonitorLearningProcessGradeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /monitor-learning-process-grades?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MonitorLearningProcessGradeCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FloatFilter firstNumber;

    private FloatFilter secondNumber;

    private FloatFilter thirdNumber;

    private FloatFilter result;

    private IntegerFilter goal;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private StringFilter guid;

    private LongFilter monitorLearningProcessLevelId;

    private LongFilter monitorLearningProcessId;

    private LongFilter monitorProcessDurationId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FloatFilter getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(FloatFilter firstNumber) {
        this.firstNumber = firstNumber;
    }

    public FloatFilter getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(FloatFilter secondNumber) {
        this.secondNumber = secondNumber;
    }

    public FloatFilter getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(FloatFilter thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public FloatFilter getResult() {
        return result;
    }

    public void setResult(FloatFilter result) {
        this.result = result;
    }

    public IntegerFilter getGoal() {
        return goal;
    }

    public void setGoal(IntegerFilter goal) {
        this.goal = goal;
    }

    public StringFilter getCreateUserLogin() {
        return createUserLogin;
    }

    public void setCreateUserLogin(StringFilter createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTimeFilter getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTimeFilter createDate) {
        this.createDate = createDate;
    }

    public StringFilter getModifyUserLogin() {
        return modifyUserLogin;
    }

    public void setModifyUserLogin(StringFilter modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTimeFilter getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(ZonedDateTimeFilter modifyDate) {
        this.modifyDate = modifyDate;
    }

    public StringFilter getGuid() {
        return guid;
    }

    public void setGuid(StringFilter guid) {
        this.guid = guid;
    }

    public LongFilter getMonitorLearningProcessLevelId() {
        return monitorLearningProcessLevelId;
    }

    public void setMonitorLearningProcessLevelId(LongFilter monitorLearningProcessLevelId) {
        this.monitorLearningProcessLevelId = monitorLearningProcessLevelId;
    }

    public LongFilter getMonitorLearningProcessId() {
        return monitorLearningProcessId;
    }

    public void setMonitorLearningProcessId(LongFilter monitorLearningProcessId) {
        this.monitorLearningProcessId = monitorLearningProcessId;
    }

    public LongFilter getMonitorProcessDurationId() {
        return monitorProcessDurationId;
    }

    public void setMonitorProcessDurationId(LongFilter monitorProcessDurationId) {
        this.monitorProcessDurationId = monitorProcessDurationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MonitorLearningProcessGradeCriteria that = (MonitorLearningProcessGradeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(firstNumber, that.firstNumber) &&
            Objects.equals(secondNumber, that.secondNumber) &&
            Objects.equals(thirdNumber, that.thirdNumber) &&
            Objects.equals(result, that.result) &&
            Objects.equals(goal, that.goal) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(monitorLearningProcessLevelId, that.monitorLearningProcessLevelId) &&
            Objects.equals(monitorLearningProcessId, that.monitorLearningProcessId) &&
            Objects.equals(monitorProcessDurationId, that.monitorProcessDurationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        firstNumber,
        secondNumber,
        thirdNumber,
        result,
        goal,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        guid,
        monitorLearningProcessLevelId,
        monitorLearningProcessId,
        monitorProcessDurationId
        );
    }

    @Override
    public String toString() {
        return "MonitorLearningProcessGradeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (firstNumber != null ? "firstNumber=" + firstNumber + ", " : "") +
                (secondNumber != null ? "secondNumber=" + secondNumber + ", " : "") +
                (thirdNumber != null ? "thirdNumber=" + thirdNumber + ", " : "") +
                (result != null ? "result=" + result + ", " : "") +
                (goal != null ? "goal=" + goal + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (monitorLearningProcessLevelId != null ? "monitorLearningProcessLevelId=" + monitorLearningProcessLevelId + ", " : "") +
                (monitorLearningProcessId != null ? "monitorLearningProcessId=" + monitorLearningProcessId + ", " : "") +
                (monitorProcessDurationId != null ? "monitorProcessDurationId=" + monitorProcessDurationId + ", " : "") +
            "}";
    }

}
