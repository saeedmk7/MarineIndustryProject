package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A FieldOfStudy.
 */
@Entity
@Table(name = "field_of_study")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FieldOfStudy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 1000)
    @Column(name = "title", length = 1000, nullable = false)
    private String title;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @Size(max = 50)
    @Column(name = "create_user_login", length = 50)
    private String createUserLogin;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Size(max = 50)
    @Column(name = "modify_user_login", length = 50)
    private String modifyUserLogin;

    @Column(name = "modify_date")
    private ZonedDateTime modifyDate;

    @OneToMany(mappedBy = "lastFieldOfStudy")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Person> people = new HashSet<>();
    @OneToMany(mappedBy = "lastFieldOfStudy")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Teacher> teachers = new HashSet<>();
    @OneToMany(mappedBy = "fieldOfStudy")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EducationalRecord> educationalRecords = new HashSet<>();
    @OneToMany(mappedBy = "lastFieldOfStudy")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Soldier> soldiers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public FieldOfStudy title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public FieldOfStudy code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public FieldOfStudy createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public FieldOfStudy createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public FieldOfStudy modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public FieldOfStudy modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public FieldOfStudy people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public FieldOfStudy addPerson(Person person) {
        this.people.add(person);
        person.setLastFieldOfStudy(this);
        return this;
    }

    public FieldOfStudy removePerson(Person person) {
        this.people.remove(person);
        person.setLastFieldOfStudy(null);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public FieldOfStudy teachers(Set<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public FieldOfStudy addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.setLastFieldOfStudy(this);
        return this;
    }

    public FieldOfStudy removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
        teacher.setLastFieldOfStudy(null);
        return this;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<EducationalRecord> getEducationalRecords() {
        return educationalRecords;
    }

    public FieldOfStudy educationalRecords(Set<EducationalRecord> educationalRecords) {
        this.educationalRecords = educationalRecords;
        return this;
    }

    public FieldOfStudy addEducationalRecord(EducationalRecord educationalRecord) {
        this.educationalRecords.add(educationalRecord);
        educationalRecord.setFieldOfStudy(this);
        return this;
    }

    public FieldOfStudy removeEducationalRecord(EducationalRecord educationalRecord) {
        this.educationalRecords.remove(educationalRecord);
        educationalRecord.setFieldOfStudy(null);
        return this;
    }

    public void setEducationalRecords(Set<EducationalRecord> educationalRecords) {
        this.educationalRecords = educationalRecords;
    }

    public Set<Soldier> getSoldiers() {
        return soldiers;
    }

    public FieldOfStudy soldiers(Set<Soldier> soldiers) {
        this.soldiers = soldiers;
        return this;
    }

    public FieldOfStudy addSoldier(Soldier soldier) {
        this.soldiers.add(soldier);
        soldier.setLastFieldOfStudy(this);
        return this;
    }

    public FieldOfStudy removeSoldier(Soldier soldier) {
        this.soldiers.remove(soldier);
        soldier.setLastFieldOfStudy(null);
        return this;
    }

    public void setSoldiers(Set<Soldier> soldiers) {
        this.soldiers = soldiers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldOfStudy fieldOfStudy = (FieldOfStudy) o;
        if (fieldOfStudy.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fieldOfStudy.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FieldOfStudy{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
