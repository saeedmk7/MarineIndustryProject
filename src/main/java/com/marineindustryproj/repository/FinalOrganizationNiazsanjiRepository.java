package com.marineindustryproj.repository;

import com.marineindustryproj.domain.FinalOrganizationNiazsanji;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the FinalOrganizationNiazsanji entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinalOrganizationNiazsanjiRepository extends JpaRepository<FinalOrganizationNiazsanji, Long>, JpaSpecificationExecutor<FinalOrganizationNiazsanji> {

    @Query(value = "select distinct final_organization_niazsanji from FinalOrganizationNiazsanji final_organization_niazsanji left join fetch final_organization_niazsanji.people left join fetch final_organization_niazsanji.documents",
        countQuery = "select count(distinct final_organization_niazsanji) from FinalOrganizationNiazsanji final_organization_niazsanji")
    Page<FinalOrganizationNiazsanji> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct final_organization_niazsanji from FinalOrganizationNiazsanji final_organization_niazsanji left join fetch final_organization_niazsanji.people left join fetch final_organization_niazsanji.documents")
    List<FinalOrganizationNiazsanji> findAllWithEagerRelationships();

    @Query("select final_organization_niazsanji from FinalOrganizationNiazsanji final_organization_niazsanji left join fetch final_organization_niazsanji.people left join fetch final_organization_niazsanji.documents where final_organization_niazsanji.id =:id")
    Optional<FinalOrganizationNiazsanji> findOneWithEagerRelationships(@Param("id") Long id);

}
