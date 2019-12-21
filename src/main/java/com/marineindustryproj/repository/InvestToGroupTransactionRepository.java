package com.marineindustryproj.repository;

import com.marineindustryproj.domain.InvestToGroupTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the InvestToGroupTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestToGroupTransactionRepository extends JpaRepository<InvestToGroupTransaction, Long>, JpaSpecificationExecutor<InvestToGroupTransaction> {

    @Query(value = "select distinct invest_to_group_transaction from InvestToGroupTransaction invest_to_group_transaction left join fetch invest_to_group_transaction.documents",
        countQuery = "select count(distinct invest_to_group_transaction) from InvestToGroupTransaction invest_to_group_transaction")
    Page<InvestToGroupTransaction> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct invest_to_group_transaction from InvestToGroupTransaction invest_to_group_transaction left join fetch invest_to_group_transaction.documents")
    List<InvestToGroupTransaction> findAllWithEagerRelationships();

    @Query("select invest_to_group_transaction from InvestToGroupTransaction invest_to_group_transaction left join fetch invest_to_group_transaction.documents where invest_to_group_transaction.id =:id")
    Optional<InvestToGroupTransaction> findOneWithEagerRelationships(@Param("id") Long id);

}
