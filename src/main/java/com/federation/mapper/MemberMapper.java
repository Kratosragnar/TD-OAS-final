package com.federation.mapper;

import com.federation.dto.response.MemberResponse;
import com.federation.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(target = "fullName", expression = "java(member.getFirstName() + \" \" + member.getLastName())")
    @Mapping(target = "seniorityDays", expression = "java(member.getSeniorityDays())")
    @Mapping(target = "collectivityId", source = "collectivity.id")
    @Mapping(target = "collectivityName", source = "collectivity.name")
    @Mapping(target = "sponsorId", source = "sponsor.id")
    @Mapping(target = "sponsorName", expression = "java(member.getSponsor() != null ? member.getSponsor().getFirstName() + \" \" + member.getSponsor().getLastName() : null)")
    MemberResponse toResponse(Member member);
}