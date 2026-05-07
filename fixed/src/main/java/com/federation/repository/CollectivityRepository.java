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
}