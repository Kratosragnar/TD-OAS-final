package com.federation.mapper;

import com.federation.dto.response.MemberResponse;
import com.federation.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-07T12:50:49+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberResponse toDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponse memberResponse = new MemberResponse();

        memberResponse.setReferees( refereesToSponsorResponses( member.getReferees() ) );
        memberResponse.setId( member.getId() );
        memberResponse.setFirstName( member.getFirstName() );
        memberResponse.setLastName( member.getLastName() );
        memberResponse.setFullName( member.getFullName() );
        memberResponse.setBirthDate( member.getBirthDate() );
        memberResponse.setGender( member.getGender() );
        memberResponse.setEmail( member.getEmail() );
        memberResponse.setPhone( member.getPhone() );
        memberResponse.setAddress( member.getAddress() );
        memberResponse.setJoinDate( member.getJoinDate() );
        memberResponse.setStatus( member.getStatus() );

        return memberResponse;
    }
}
