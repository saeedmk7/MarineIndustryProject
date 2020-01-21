package com.marineindustryproj.repository;

import com.marineindustryproj.domain.InstructionAuthority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InstructionAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstructionAuthorityRepository extends JpaRepository<InstructionAuthority, Long>, JpaSpecificationExecutor<InstructionAuthority> {

}
