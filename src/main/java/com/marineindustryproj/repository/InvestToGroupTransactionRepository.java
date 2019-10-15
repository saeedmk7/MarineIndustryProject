package com.marineindustryproj.repository;

import com.marineindustryproj.domain.InvestToGroupTransaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InvestToGroupTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestToGroupTransactionRepository extends JpaRepository<InvestToGroupTransaction, Long>, JpaSpecificationExecutor<InvestToGroupTransaction> {

}
