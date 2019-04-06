package com.marineindustryproj.repository;

import com.marineindustryproj.domain.RequestOrganizationNiazsanji;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the RequestOrganizationNiazsanji entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestOrganizationNiazsanjiRepository extends JpaRepository<RequestOrganizationNiazsanji, Long>, JpaSpecificationExecutor<RequestOrganizationNiazsanji> {

    @Query(value = "select distinct request_organization_niazsanji from RequestOrganizationNiazsanji request_organization_niazsanji left join fetch request_organization_niazsanji.people left join fetch request_organization_niazsanji.documents",
        countQuery = "select count(distinct request_organization_niazsanji) from RequestOrganizationNiazsanji request_organization_niazsanji")
    Page<RequestOrganizationNiazsanji> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct request_organization_niazsanji from RequestOrganizationNiazsanji request_organization_niazsanji left join fetch request_organization_niazsanji.people left join fetch request_organization_niazsanji.documents")
    List<RequestOrganizationNiazsanji> findAllWithEagerRelationships();

    @Query("select request_organization_niazsanji from RequestOrganizationNiazsanji request_organization_niazsanji left join fetch request_organization_niazsanji.people left join fetch request_organization_niazsanji.documents where request_organization_niazsanji.id =:id")
    Optional<RequestOrganizationNiazsanji> findOneWithEagerRelationships(@Param("id") Long id);

}
