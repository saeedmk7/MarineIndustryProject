package com.marineindustryproj.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.config.Constants;
import com.marineindustryproj.domain.Authority;
import com.marineindustryproj.security.AuthoritiesConstants;
import com.marineindustryproj.service.UserService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AuthorityResource {

    private final Logger log = LoggerFactory.getLogger(AuthorityResource.class);

    private final UserService userService;


    public AuthorityResource(UserService userService) {

        this.userService = userService;

    }

    /**
     * @return a string list of the all of the roles
     */
    @GetMapping("/authorities")
    @Timed
    public ResponseEntity<List<Authority>> getAuthorities() {
        return new ResponseEntity<>(userService.getAuthorities(), null, HttpStatus.OK);
        //return userService.getAuthorities();
    }

    /**
     * POST  /users/authority  : Creates a new authority.
     *
     * @param authority the authority to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) if the login or email is already in use
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws BadRequestAlertException 400 (Bad Request) if the login or email is already in use
     */
    @PostMapping("/authority")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Authority> createAuthority(@Valid @RequestBody Authority authority) throws URISyntaxException {
        log.debug("REST request to save Authority : {}", authority);

            Authority newAuthority = userService.createAuthority(authority);
        return ResponseEntity.created(new URI("/api/authority/" + newAuthority.getName()))
            .headers(HeaderUtil.createAlert( "userManagement.created", newAuthority.getName()))
            .body(newAuthority);
    }
    /**
     * DELETE /users/:login : delete the "login" User.
     *
     * @param name the name of the user to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/authority/{name:" + Constants.AUTHORITY_REGEX + "}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteAuthority(@PathVariable String name) {
        log.debug("REST request to delete Authority: {}", name);
        userService.deleteAuthority(name);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "userManagement.deleted", name)).build();
    }
}
