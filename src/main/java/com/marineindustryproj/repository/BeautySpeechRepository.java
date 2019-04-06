package com.marineindustryproj.repository;

import com.marineindustryproj.domain.BeautySpeech;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BeautySpeech entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeautySpeechRepository extends JpaRepository<BeautySpeech, Long>, JpaSpecificationExecutor<BeautySpeech> {

}
