package com.marineindustryproj.service.mapper;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.dto.UsersRequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UsersRequest and its DTO UsersRequestDTO.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class})
public interface UsersRequestMapper extends EntityMapper<UsersRequestDTO, UsersRequest> {



    default UsersRequest fromId(Long id) {
        if (id == null) {
            return null;
        }
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setId(id);
        return usersRequest;
    }
}
