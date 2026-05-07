package com.federation.mapper;

import com.federation.dto.response.CollectivityResponse;
<<<<<<< HEAD
import com.federation.entity.Collectivity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CollectivityMapper {

    @Mapping(target = "memberCount", expression = "java(collectivity.getMembers() != null ? collectivity.getMembers().size() : 0)")
    @Mapping(target = "activeMandatesCount", expression = "java(collectivity.getMandates() != null ? collectivity.getMandates().stream().filter(m -> m.getIsActive() != null && m.getIsActive()).count() : 0L)")
    CollectivityResponse toResponse(Collectivity collectivity);
=======
import com.federation.dto.response.CollectivityStructureResponse;
import com.federation.dto.response.MemberResponse;
import com.federation.entity.Collectivity;
import com.federation.entity.Mandate;
import com.federation.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CollectivityMapper {

    @Autowired
    protected MemberMapper memberMapper;

    @Mapping(target = "members", source = "members")
    @Mapping(target = "structure", source = "mandates", qualifiedByName = "mandatesToStructure")
    public abstract CollectivityResponse toDto(Collectivity collectivity);

    @Named("mandatesToStructure")
    CollectivityStructureResponse mandatesToStructure(List<Mandate> mandates) {
        if (mandates == null || mandates.isEmpty()) return null;
        CollectivityStructureResponse struct = new CollectivityStructureResponse();
        for (Mandate m : mandates) {
            Member member = m.getMember();
            MemberResponse dto = memberMapper.toDto(member);
            switch (m.getRole()) {
                case PRESIDENT -> struct.setPresident(dto);
                case VICE_PRESIDENT -> struct.setVicePresident(dto);
                case TREASURER -> struct.setTreasurer(dto);
                case SECRETARY -> struct.setSecretary(dto);
            }
        }
        return struct;
    }
>>>>>>> d7e79cd (Fourth commit)
}