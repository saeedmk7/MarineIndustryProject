package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MediaProductType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MediaProductType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MediaProductTypeRepository extends JpaRepository<MediaProductType, Long>, JpaSpecificationExecutor<MediaProductType> {

}
