package com.federation.repository;

import com.federation.entity.Activity;
import com.federation.enums.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, UUID> {

    List<Activity> findByCollectivityId(UUID collectivityId);

    List<Activity> findByCollectivityIdOrderByActivityDateDesc(UUID collectivityId);

    List<Activity> findByType(ActivityType type);

    List<Activity> findByCollectivityIdAndType(UUID collectivityId, ActivityType type);

    List<Activity> findByActivityDateBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT a FROM Activity a WHERE a.collectivity.id = :collectivityId AND a.activityDate > :date")
    List<Activity> findUpcomingActivitiesByCollectivityId(@Param("collectivityId") UUID collectivityId, @Param("date") LocalDateTime date);

    @Query("SELECT COUNT(a) FROM Activity a WHERE a.collectivity.id = :collectivityId")
    Long countActivitiesByCollectivityId(@Param("collectivityId") UUID collectivityId);
    @Query("SELECT COUNT(a) FROM Activity a WHERE a.collectivity.id = :collectivityId AND a.type = :type")
    Long countByCollectivityIdAndType(@Param("collectivityId") UUID collectivityId, @Param("type") ActivityType type);
}