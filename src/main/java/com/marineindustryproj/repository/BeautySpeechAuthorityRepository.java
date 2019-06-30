package com.marineindustryproj.repository;

import com.marineindustryproj.domain.BeautySpeechAuthority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BeautySpeechAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeautySpeechAuthorityRepository extends JpaRepository<BeautySpeechAuthority, Long>, JpaSpecificationExecutor<BeautySpeechAuthority> {

}
