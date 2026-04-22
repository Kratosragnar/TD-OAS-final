package com.federation.mapper;

import com.federation.dto.MemberDto;
import com.federation.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {


    @Mapping(source = "referees", target = "refereeIds", qualifiedByName = "membersToIds")
    MemberDto toDto(Member member);


    @Mapping(source = "refereeIds", target = "referees", qualifiedByName = "idsToMembers")
    Member toEntity(MemberDto dto);

    List<MemberDto> toDtoList(List<Member> members);


    @Named("membersToIds")
    default List<UUID> membersToIds(List<Member> members) {
        if (members == null) return null;
        return members.stream().map(Member::getId).collect(Collectors.toList());
    }


    @Named("idsToMembers")
    default List<Member> idsToMembers(List<UUID> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id -> Member.builder().id(id).build())
                .collect(Collectors.toList());
    }
}