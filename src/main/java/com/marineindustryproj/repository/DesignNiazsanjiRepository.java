package com.marineindustryproj.repository;

import com.marineindustryproj.domain.DesignNiazsanji;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DesignNiazsanji entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DesignNiazsanjiRepository extends JpaRepository<DesignNiazsanji, Long>, JpaSpecificationExecutor<DesignNiazsanji> {

    @Query(value = "select distinct design_niazsanji from DesignNiazsanji design_niazsanji left join fetch design_niazsanji.restrictions",
        countQuery = "select count(distinct design_niazsanji) from DesignNiazsanji design_niazsanji")
    Page<DesignNiazsanji> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct design_niazsanji from DesignNiazsanji design_niazsanji left join fetch design_niazsanji.restrictions")
    List<DesignNiazsanji> findAllWithEagerRelationships();

    @Query("select design_niazsanji from DesignNiazsanji design_niazsanji left join fetch design_niazsanji.restrictions where design_niazsanji.id =:id")
    Optional<DesignNiazsanji> findOneWithEagerRelationships(@Param("id") Long id);

}
