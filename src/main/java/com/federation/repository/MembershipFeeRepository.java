package com.federation.repository;

import com.federation.entity.MembershipFee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MembershipFeeRepository extends JpaRepository<MembershipFee, UUID> {
    List<MembershipFee> findByCollectivityId(UUID collectivityId);
}