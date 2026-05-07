package com.federation.mapper;

import com.federation.dto.response.MemberResponse;
import com.federation.entity.Collectivity;
import com.federation.entity.Member;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-21T15:18:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberResponse toResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponse.MemberResponseBuilder memberResponse = MemberResponse.builder();

        memberResponse.collectivityId( memberCollectivityId( member ) );
        memberResponse.collectivityName( memberCollectivityName( member ) );
        memberResponse.sponsorId( memberSponsorId( member ) );
        memberResponse.id( member.getId() );
        memberResponse.firstName( member.getFirstName() );
        memberResponse.lastName( member.getLastName() );
        memberResponse.birthDate( member.getBirthDate() );
        memberResponse.email( member.getEmail() );
        memberResponse.phone( member.getPhone() );
        memberResponse.address( member.getAddress() );
        memberResponse.joinDate( member.getJoinDate() );
        memberResponse.status( member.getStatus() );

        memberResponse.fullName( member.getFirstName() + " " + member.getLastName() );
        memberResponse.seniorityDays( member.getSeniorityDays() );
        memberResponse.sponsorName( member.getSponsor() != null ? member.getSponsor().getFirstName() + " " + member.getSponsor().getLastName() : null );

        return memberResponse.build();
    }

    private UUID memberCollectivityId(Member member) {
        if ( member == null ) {
            return null;
        }
        Collectivity collectivity = member.getCollectivity();
        if ( collectivity == null ) {
            return null;
        }
        UUID id = collectivity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String memberCollectivityName(Member member) {
        if ( member == null ) {
            return null;
        }
        Collectivity collectivity = member.getCollectivity();
        if ( collectivity == null ) {
            return null;
        }
        String name = collectivity.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private UUID memberSponsorId(Member member) {
        if ( member == null ) {
            return null;
        }
        Member sponsor = member.getSponsor();
        if ( sponsor == null ) {
            return null;
        }
        UUID id = sponsor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
