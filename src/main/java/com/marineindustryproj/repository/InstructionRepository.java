package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Instruction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Instruction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Long>, JpaSpecificationExecutor<Instruction> {

    @Query(value = "select distinct instruction from Instruction instruction left join fetch instruction.documents",
        countQuery = "select count(distinct instruction) from Instruction instruction")
    Page<Instruction> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct instruction from Instruction instruction left join fetch instruction.documents")
    List<Instruction> findAllWithEagerRelationships();

    @Query("select instruction from Instruction instruction left join fetch instruction.documents where instruction.id =:id")
    Optional<Instruction> findOneWithEagerRelationships(@Param("id") Long id);

}
