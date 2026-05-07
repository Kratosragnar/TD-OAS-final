package com.federation.mapper;

import com.federation.dto.response.MemberResponse;
<<<<<<< HEAD
import com.federation.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
=======
import com.federation.dto.response.SponsorResponse;
import com.federation.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;
>>>>>>> d7e79cd (Fourth commit)

@Mapper(componentModel = "spring")
public interface MemberMapper {

<<<<<<< HEAD
    @Mapping(target = "fullName", expression = "java(member.getFirstName() + \" \" + member.getLastName())")
    @Mapping(target = "seniorityDays", expression = "java(member.getSeniorityDays())")
    @Mapping(target = "collectivityId", source = "collectivity.id")
    @Mapping(target = "collectivityName", source = "collectivity.name")
    @Mapping(target = "sponsorId", source = "sponsor.id")
    @Mapping(target = "sponsorName", expression = "java(member.getSponsor() != null ? member.getSponsor().getFirstName() + \" \" + member.getSponsor().getLastName() : null)")
    MemberResponse toResponse(Member member);
=======
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
>>>>>>> d7e79cd (Fourth commit)
}