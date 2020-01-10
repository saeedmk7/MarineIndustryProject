package com.marineindustryproj.web.rest;

import com.marineindustryproj.config.Constants;
import com.marineindustryproj.domain.Authority;
import com.marineindustryproj.domain.User;
import com.marineindustryproj.repository.UserRepository;
import com.marineindustryproj.security.AuthoritiesConstants;
import com.marineindustryproj.service.MailService;
import com.marineindustryproj.service.UserService;
import com.marineindustryproj.service.dto.UserDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.errors.EmailAlreadyUsedException;
import com.marineindustryproj.web.rest.errors.ErrorConstants;
import com.marineindustryproj.web.rest.errors.LoginAlreadyUsedException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * REST controller for managing users.
 * <p>
 * This class accesses the User entity, and needs to fetch its collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between User and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserResource(UserService userService,
                        UserRepository userRepository,
                        MailService mailService,
                        PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * POST  /users  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param userDTO the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) if the login or email is already in use
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws BadRequestAlertException 400 (Bad Request) if the login or email is already in use
     */
    @PostMapping("/users")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);

        if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
            // Lowercase the user login before comparing with database
        } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        }

        Long userDTOPersonId = userDTO.getPersonId();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Long personId = user.getPersonId();
            if(userDTOPersonId.equals(personId)) {
                    throw new BadRequestAlertException(ErrorConstants.PERSON_ALREADY_USED_TYPE, "Person Id already used!", "userManagement", "userexists"); //PERSON_ALREADY_USED_TYPE
            }
        }

            User newUser = userService.createUser(userDTO);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getLogin()))
                .headers(HeaderUtil.createAlert( "userManagement.created", newUser.getLogin()))
                .body(newUser);
    }
    /**
     * POST   /users/reset-password : Send an login to reset the password of the user
     *
     * @param userDTO the login of the user
     */
    @PostMapping(path = "/users/reset-password")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<UserDTO> passwordReset(@Valid @RequestBody UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode("123456"));
        Optional<UserDTO> updatedUser = userService.updateUser(userDTO);
        return ResponseUtil.wrapOrNotFound(updatedUser,
                                           HeaderUtil.createEntityUpdateAlert("userManagement.updated", userDTO.getLogin()));
    }
    /**
     * PUT /users : Updates an existing User.
     *
     * @param userDTO the user to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated user
     * @throws EmailAlreadyUsedException 400 (Bad Request) if the email is already in use
     * @throws LoginAlreadyUsedException 400 (Bad Request) if the login is already in use
     */
    @PutMapping("/users")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        log.debug("REST request to update User : {}", userDTO);
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new EmailAlreadyUsedException();
        }
        existingUser = userRepository.findOneByLogin(userDTO.getLogin().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new LoginAlreadyUsedException();
        }
        Long userDTOPersonId = userDTO.getPersonId();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Long personId = user.getPersonId();
            Long userId = user.getId();
            Long userDTOId = userDTO.getId();
            if(!(userId.equals(userDTOId))) {
                if (userDTOPersonId.equals(personId)) {
                        throw new BadRequestAlertException(ErrorConstants.PERSON_ALREADY_USED_TYPE,
                                                           "Person Id already used!",
                                                           "userManagement",
                                                           "userexists"); //PERSON_ALREADY_USED_TYPE
                    }
            }
        }
        /*List<User> userStream = users.stream().filter(a -> a.getPersonId() == userDTO.getPersonId()).collect(Collectors.toList());
        //existingUser = userStream.findFirst(); //.collect(Collectors.toList())
        if((!userStream.isEmpty()) && (!userStream.get(0).getId().equals(userDTO.getId()))){

        }*/

        Optional<UserDTO> updatedUser = userService.updateUser(userDTO);

        return ResponseUtil.wrapOrNotFound(updatedUser,
            HeaderUtil.createAlert("userManagement.updated", userDTO.getLogin()));
    }

    /**
     * GET /users : get all users.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all users
     */
    @GetMapping("/users")
    @Timed
    public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) {
        final Page<UserDTO> page = userService.getAllManagedUsers(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    /**
     * GET /users : get all users.
     *
     * @param authority
     * @return the ResponseEntity with status 200 (OK) and with body all users
     */
    @GetMapping("/users/ByAuthority/{authority}")
    @Timed
    public ResponseEntity<List<UserDTO>> getAllUsersByAuthorityIs(@PathVariable String authority) {
        List<UserDTO> users = userService.getAllUsersByAuthoritiesIs(authority);
        return new ResponseEntity<>(users, null, HttpStatus.OK);
    }
    /**
     * GET /users : get all users.
     *
     * @return the ResponseEntity with status 200 (OK) and with body all users
    @GetMapping("/users/all")
    @Timed
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }*/
    /**
     * GET /users/:login : get the "login" user.
     *
     * @param login the login of the user to find
     * @return the ResponseEntity with status 200 (OK) and with body the "login" user, or with status 404 (Not Found)
     */
    @GetMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")
    @Timed
    public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
        log.debug("REST request to get User : {}", login);
        return ResponseUtil.wrapOrNotFound(
            userService.getUserWithAuthoritiesByLogin(login)
                .map(UserDTO::new));
    }

    /**
     * GET /users/:login : get the "login" user.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the "login" user, or with status 404 (Not Found)
     */
    @GetMapping("/users/person/{personId}")
    @Timed
    public ResponseEntity<UserDTO> getUser(@PathVariable Long personId) {
        log.debug("REST request to get User : {}", personId);
        return ResponseUtil.wrapOrNotFound(
            userService.getUserWithAuthoritiesByPersonId(personId)
                .map(UserDTO::new));
    }

    /**
     * DELETE /users/:login : delete the "login" User.
     *
     * @param login the login of the user to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/users/{login:" + Constants.LOGIN_REGEX + "}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        log.debug("REST request to delete User: {}", login);
        userService.deleteUser(login);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "userManagement.deleted", login)).build();
    }
}
