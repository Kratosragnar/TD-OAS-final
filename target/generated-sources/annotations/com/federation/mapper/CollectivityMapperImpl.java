package com.federation.mapper;

import com.federation.dto.MemberDto;
import com.federation.dto.response.CollectivityResponse;
import com.federation.entity.Collectivity;
import com.federation.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-22T16:54:27+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CollectivityMapperImpl implements CollectivityMapper {

    @Override
    public CollectivityResponse toResponse(Collectivity collectivity) {
        if ( collectivity == null ) {
            return null;
        }

        CollectivityResponse.CollectivityResponseBuilder collectivityResponse = CollectivityResponse.builder();

        collectivityResponse.id( collectivity.getId() );
        collectivityResponse.name( collectivity.getName() );
        collectivityResponse.city( collectivity.getCity() );
        collectivityResponse.specialty( collectivity.getSpecialty() );
        collectivityResponse.creationDate( collectivity.getCreationDate() );
        collectivityResponse.validatedByFederation( collectivity.getValidatedByFederation() );
        collectivityResponse.status( collectivity.getStatus() );
        collectivityResponse.members( memberListToMemberDtoList( collectivity.getMembers() ) );

        collectivityResponse.memberCount( collectivity.getMembers() != null ? collectivity.getMembers().size() : 0 );
        collectivityResponse.activeMandatesCount( collectivity.getMandates() != null ? collectivity.getMandates().stream().filter(m -> m.getIsActive() != null && m.getIsActive()).count() : 0L );

        return collectivityResponse.build();
    }

    protected MemberDto memberToMemberDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDto memberDto = new MemberDto();

        memberDto.setId( member.getId() );
        memberDto.setFirstName( member.getFirstName() );
        memberDto.setLastName( member.getLastName() );
        memberDto.setBirthDate( member.getBirthDate() );
        memberDto.setGender( member.getGender() );
        memberDto.setAddress( member.getAddress() );
        memberDto.setProfession( member.getProfession() );
        memberDto.setPhoneNumber( member.getPhoneNumber() );
        memberDto.setEmail( member.getEmail() );
        memberDto.setOccupation( member.getOccupation() );
        memberDto.setAdhesionDate( member.getAdhesionDate() );

        return memberDto;
    }

    protected List<MemberDto> memberListToMemberDtoList(List<Member> list) {
        if ( list == null ) {
            return null;
        }

        List<MemberDto> list1 = new ArrayList<MemberDto>( list.size() );
        for ( Member member : list ) {
            list1.add( memberToMemberDto( member ) );
        }

        return list1;
    }
}
