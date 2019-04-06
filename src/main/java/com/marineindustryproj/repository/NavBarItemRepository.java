package com.marineindustryproj.repository;

import com.marineindustryproj.domain.NavBarItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NavBarItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NavBarItemRepository extends JpaRepository<NavBarItem, Long>, JpaSpecificationExecutor<NavBarItem> {

}
