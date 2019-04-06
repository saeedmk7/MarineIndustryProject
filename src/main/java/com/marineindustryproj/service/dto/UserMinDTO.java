package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.marineindustryproj.config.Constants;
import com.marineindustryproj.domain.Authority;
import com.marineindustryproj.domain.User;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserMinDTO implements Serializable {

    private Long id;

    private Long personId;

    private String login;

    private Set<String> authorities;

    public UserMinDTO() {
        // Empty constructor needed for Jackson.
    }

    public UserMinDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.personId = user.getPersonId();
        this.authorities = user.getAuthorities().stream()
            .map(Authority::getName)
            .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            "personId=" + personId +
            ", authorities=" + authorities +
            "}";
    }
}
