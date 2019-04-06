package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Announcement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, JpaSpecificationExecutor<Announcement> {

    @Query(value = "select distinct announcement from Announcement announcement left join fetch announcement.documents",
        countQuery = "select count(distinct announcement) from Announcement announcement")
    Page<Announcement> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct announcement from Announcement announcement left join fetch announcement.documents")
    List<Announcement> findAllWithEagerRelationships();

    @Query("select announcement from Announcement announcement left join fetch announcement.documents where announcement.id =:id")
    Optional<Announcement> findOneWithEagerRelationships(@Param("id") Long id);

}
