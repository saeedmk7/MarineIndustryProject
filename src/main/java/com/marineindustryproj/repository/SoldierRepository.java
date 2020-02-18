package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Soldier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Soldier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoldierRepository extends JpaRepository<Soldier, Long>, JpaSpecificationExecutor<Soldier> {

    @Query(value = "select distinct soldier from Soldier soldier left join fetch soldier.documents",
        countQuery = "select count(distinct soldier) from Soldier soldier")
    Page<Soldier> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct soldier from Soldier soldier left join fetch soldier.documents")
    List<Soldier> findAllWithEagerRelationships();

    @Query("select soldier from Soldier soldier left join fetch soldier.documents where soldier.id =:id")
    Optional<Soldier> findOneWithEagerRelationships(@Param("id") Long id);

}
