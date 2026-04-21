package com.federation.mapper;

import com.federation.dto.response.CollectivityResponse;
import com.federation.entity.Collectivity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-21T15:18:54+0300",
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

        collectivityResponse.memberCount( collectivity.getMembers() != null ? collectivity.getMembers().size() : 0 );
        collectivityResponse.activeMandatesCount( collectivity.getMandates() != null ? collectivity.getMandates().stream().filter(m -> m.getIsActive() != null && m.getIsActive()).count() : 0L );

        return collectivityResponse.build();
    }
}
