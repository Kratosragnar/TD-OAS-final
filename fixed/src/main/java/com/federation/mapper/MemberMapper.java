package com.federation.mapper;

import com.federation.dto.response.MemberResponse;
import com.federation.dto.response.SponsorResponse;
import com.federation.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(source = "referees", target = "referees", qualifiedByName = "refereesToSponsorResponses")
    MemberResponse toDto(Member member);

    @Named("refereesToSponsorResponses")
    default List<SponsorResponse> refereesToSponsorResponses(List<Member> referees) {
        if (referees == null) return null;
        return referees.stream().map(ref -> {
            SponsorResponse sr = new SponsorResponse();
            sr.setSponsorId(ref.getId());
            sr.setSponsorName(ref.getFirstName() + " " + ref.getLastName());
            return sr;
        }).collect(Collectors.toList());
    }
}