package com.marineindustryproj.repository;

import com.marineindustryproj.domain.UsersRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the UsersRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsersRequestRepository extends JpaRepository<UsersRequest, Long>, JpaSpecificationExecutor<UsersRequest> {

    @Query(value = "select distinct users_request from UsersRequest users_request left join fetch users_request.documents",
        countQuery = "select count(distinct users_request) from UsersRequest users_request")
    Page<UsersRequest> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct users_request from UsersRequest users_request left join fetch users_request.documents")
    List<UsersRequest> findAllWithEagerRelationships();

    @Query("select users_request from UsersRequest users_request left join fetch users_request.documents where users_request.id =:id")
    Optional<UsersRequest> findOneWithEagerRelationships(@Param("id") Long id);

}
