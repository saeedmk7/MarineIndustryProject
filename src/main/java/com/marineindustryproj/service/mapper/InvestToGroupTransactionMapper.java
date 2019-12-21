package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.InvestToGroupTransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity InvestToGroupTransaction and its DTO InvestToGroupTransactionDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class, OrganizationChartMapper.class})
public interface InvestToGroupTransactionMapper extends EntityMapper<InvestToGroupTransactionDTO, InvestToGroupTransaction> {

    @Mapping(source = "organizationChart.id", target = "organizationChartId")
    @Mapping(source = "organizationChart.title", target = "organizationChartTitle")
    InvestToGroupTransactionDTO toDto(InvestToGroupTransaction investToGroupTransaction);

    @Mapping(source = "organizationChartId", target = "organizationChart")
    InvestToGroupTransaction toEntity(InvestToGroupTransactionDTO investToGroupTransactionDTO);

    default InvestToGroupTransaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvestToGroupTransaction investToGroupTransaction = new InvestToGroupTransaction();
        investToGroupTransaction.setId(id);
        return investToGroupTransaction;
    }
}
