<<<<<<< HEAD
package com.federation.repository;

import com.federation.entity.Collectivity;
import com.federation.enums.CollectivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CollectivityRepository extends JpaRepository<Collectivity, UUID> {

    List<Collectivity> findByStatus(CollectivityStatus status);

    List<Collectivity> findByCity(String city);

    @Query("SELECT c FROM Collectivity c WHERE c.validatedByFederation = true")
    List<Collectivity> findAllValidated();

    @Query("SELECT c FROM Collectivity c LEFT JOIN FETCH c.members WHERE c.id = :id")
    Optional<Collectivity> findByIdWithMembers(@Param("id") UUID id);

    @Query("SELECT COUNT(m) FROM Collectivity c JOIN c.members m WHERE c.id = :id")
    Long countMembersByCollectivityId(@Param("id") UUID id);

    boolean existsByNameAndCity(String name, String city);
=======
// CollectivityRepository.java
package com.federation.repository;

import com.federation.entity.Collectivity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CollectivityRepository extends JpaRepository<Collectivity, UUID> {
    boolean existsByNumber(String number);
    boolean existsByName(String name);
    Optional<Collectivity> findByNumber(String number);
    Optional<Collectivity> findByName(String name);
>>>>>>> d7e79cd (Fourth commit)
}