package com.federation.mapper;

import com.federation.dto.MemberDto;
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
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDto toDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDto memberDto = new MemberDto();

        memberDto.setRefereeIds( membersToIds( member.getReferees() ) );
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

    @Override
    public Member toEntity(MemberDto dto) {
        if ( dto == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.referees( idsToMembers( dto.getRefereeIds() ) );
        member.id( dto.getId() );
        member.firstName( dto.getFirstName() );
        member.lastName( dto.getLastName() );
        member.birthDate( dto.getBirthDate() );
        member.gender( dto.getGender() );
        member.address( dto.getAddress() );
        member.profession( dto.getProfession() );
        member.phoneNumber( dto.getPhoneNumber() );
        member.email( dto.getEmail() );
        member.occupation( dto.getOccupation() );
        member.adhesionDate( dto.getAdhesionDate() );

        return member.build();
    }

    @Override
    public List<MemberDto> toDtoList(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberDto> list = new ArrayList<MemberDto>( members.size() );
        for ( Member member : members ) {
            list.add( toDto( member ) );
        }

        return list;
    }
}
