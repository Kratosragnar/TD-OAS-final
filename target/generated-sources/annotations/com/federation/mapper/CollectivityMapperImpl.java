package com.federation.mapper;

import com.federation.dto.response.CollectivityResponse;
<<<<<<< HEAD
import com.federation.entity.Collectivity;
=======
import com.federation.dto.response.MemberResponse;
import com.federation.dto.response.SponsorResponse;
import com.federation.entity.Collectivity;
import com.federation.entity.Member;
import java.util.ArrayList;
import java.util.List;
>>>>>>> d7e79cd (Fourth commit)
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2026-04-21T15:18:54+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CollectivityMapperImpl implements CollectivityMapper {

    @Override
    public CollectivityResponse toResponse(Collectivity collectivity) {
=======
    date = "2026-05-07T12:52:29+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CollectivityMapperImpl extends CollectivityMapper {

    @Override
    public CollectivityResponse toDto(Collectivity collectivity) {
>>>>>>> d7e79cd (Fourth commit)
        if ( collectivity == null ) {
            return null;
        }

<<<<<<< HEAD
        CollectivityResponse.CollectivityResponseBuilder collectivityResponse = CollectivityResponse.builder();

        collectivityResponse.id( collectivity.getId() );
        collectivityResponse.name( collectivity.getName() );
        collectivityResponse.city( collectivity.getCity() );
        collectivityResponse.specialty( collectivity.getSpecialty() );
        collectivityResponse.creationDate( collectivity.getCreationDate() );
        collectivityResponse.validatedByFederation( collectivity.getValidatedByFederation() );
        collectivityResponse.status( collectivity.getStatus() );

        collectivityResponse.memberCount( collectivity.getMembers() != null ? collectivity.getMembers().size() : 0 );
        collectivityResponse.activeMandatesCount( collectivity.getMandates() != null ? collectivity.getMandates().stream().filter(m -> m.getIsActive() != null && m.getIsActive()).count() : 0L );

        return collectivityResponse.build();
=======
        CollectivityResponse collectivityResponse = new CollectivityResponse();

        collectivityResponse.setMembers( memberListToMemberResponseList( collectivity.getMembers() ) );
        collectivityResponse.setStructure( mandatesToStructure( collectivity.getMandates() ) );
        collectivityResponse.setId( collectivity.getId() );
        collectivityResponse.setNumber( collectivity.getNumber() );
        collectivityResponse.setName( collectivity.getName() );
        collectivityResponse.setCity( collectivity.getCity() );
        collectivityResponse.setSpecialty( collectivity.getSpecialty() );
        collectivityResponse.setCreationDate( collectivity.getCreationDate() );
        collectivityResponse.setValidatedByFederation( collectivity.getValidatedByFederation() );
        collectivityResponse.setStatus( collectivity.getStatus() );

        return collectivityResponse;
    }

    protected SponsorResponse memberToSponsorResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        SponsorResponse sponsorResponse = new SponsorResponse();

        return sponsorResponse;
    }

    protected List<SponsorResponse> memberListToSponsorResponseList(List<Member> list) {
        if ( list == null ) {
            return null;
        }

        List<SponsorResponse> list1 = new ArrayList<SponsorResponse>( list.size() );
        for ( Member member : list ) {
            list1.add( memberToSponsorResponse( member ) );
        }

        return list1;
    }

    protected MemberResponse memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponse memberResponse = new MemberResponse();

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
        memberResponse.setReferees( memberListToSponsorResponseList( member.getReferees() ) );

        return memberResponse;
    }

    protected List<MemberResponse> memberListToMemberResponseList(List<Member> list) {
        if ( list == null ) {
            return null;
        }

        List<MemberResponse> list1 = new ArrayList<MemberResponse>( list.size() );
        for ( Member member : list ) {
            list1.add( memberToMemberResponse( member ) );
        }

        return list1;
>>>>>>> d7e79cd (Fourth commit)
    }
}
