package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * A NavBarItem.
 */
@Entity
@Table(name = "nav_bar_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NavBarItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
    private String description;

    @NotNull
    @Size(max = 1024)
    @Column(name = "url", length = 1024, nullable = false)
    private String url;

    @Size(max = 50)
    @Column(name = "faicon", length = 50)
    private String faicon;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "display_order")
    private Integer displayOrder;

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

    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NavBarItem> navBarItems = new HashSet<>();
    @OneToMany(mappedBy = "navBarItem")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NavBarItemAuthority> navBarItemAuthorities = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("navBarItems")
    private NavBarItem parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public NavBarItem title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public NavBarItem code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public NavBarItem description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public NavBarItem url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFaicon() {
        return faicon;
    }

    public NavBarItem faicon(String faicon) {
        this.faicon = faicon;
        return this;
    }

    public void setFaicon(String faicon) {
        this.faicon = faicon;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public NavBarItem isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public NavBarItem displayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public NavBarItem createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public NavBarItem createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public NavBarItem modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public NavBarItem modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<NavBarItem> getNavBarItems() {
        return navBarItems;
    }

    public NavBarItem navBarItems(Set<NavBarItem> navBarItems) {
        this.navBarItems = navBarItems;
        return this;
    }

    public NavBarItem addNavBarItem(NavBarItem navBarItem) {
        this.navBarItems.add(navBarItem);
        navBarItem.setParent(this);
        return this;
    }

    public NavBarItem removeNavBarItem(NavBarItem navBarItem) {
        this.navBarItems.remove(navBarItem);
        navBarItem.setParent(null);
        return this;
    }

    public void setNavBarItems(Set<NavBarItem> navBarItems) {
        this.navBarItems = navBarItems;
    }

    public Set<NavBarItemAuthority> getNavBarItemAuthorities() {
        return navBarItemAuthorities;
    }

    public NavBarItem navBarItemAuthorities(Set<NavBarItemAuthority> navBarItemAuthorities) {
        this.navBarItemAuthorities = navBarItemAuthorities;
        return this;
    }

    public NavBarItem addNavBarItemAuthority(NavBarItemAuthority navBarItemAuthority) {
        this.navBarItemAuthorities.add(navBarItemAuthority);
        navBarItemAuthority.setNavBarItem(this);
        return this;
    }

    public NavBarItem removeNavBarItemAuthority(NavBarItemAuthority navBarItemAuthority) {
        this.navBarItemAuthorities.remove(navBarItemAuthority);
        navBarItemAuthority.setNavBarItem(null);
        return this;
    }

    public void setNavBarItemAuthorities(Set<NavBarItemAuthority> navBarItemAuthorities) {
        this.navBarItemAuthorities = navBarItemAuthorities;
    }

    public NavBarItem getParent() {
        return parent;
    }

    public NavBarItem parent(NavBarItem navBarItem) {
        this.parent = navBarItem;
        return this;
    }

    public void setParent(NavBarItem navBarItem) {
        this.parent = navBarItem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NavBarItem navBarItem = (NavBarItem) o;
        if (navBarItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), navBarItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NavBarItem{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", url='" + getUrl() + "'" +
            ", faicon='" + getFaicon() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
