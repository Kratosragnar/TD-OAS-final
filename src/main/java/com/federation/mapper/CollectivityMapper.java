package com.federation.mapper;

import com.federation.dto.response.CollectivityResponse;
import com.federation.entity.Collectivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CollectivityMapper {

    @Mapping(target = "memberCount", expression = "java(collectivity.getMembers() != null ? collectivity.getMembers().size() : 0)")
    @Mapping(target = "activeMandatesCount", expression = "java(collectivity.getMandates() != null ? collectivity.getMandates().stream().filter(m -> m.getIsActive() != null && m.getIsActive()).count() : 0L)")
    CollectivityResponse toResponse(Collectivity collectivity);
}