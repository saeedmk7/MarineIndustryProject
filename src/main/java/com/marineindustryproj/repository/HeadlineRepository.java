package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Headline;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Headline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HeadlineRepository extends JpaRepository<Headline, Long>, JpaSpecificationExecutor<Headline> {

}
