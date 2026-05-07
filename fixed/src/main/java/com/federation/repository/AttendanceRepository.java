package com.federation.repository;

import com.federation.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {

    List<Attendance> findByActivityId(UUID activityId);

    List<Attendance> findByMemberId(UUID memberId);

    Optional<Attendance> findByActivityIdAndMemberId(UUID activityId, UUID memberId);

    List<Attendance> findByActivityIdAndPresentTrue(UUID activityId);

    List<Attendance> findByActivityIdAndPresentFalse(UUID activityId);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.member.id = :memberId AND a.present = true")
    Long countAttendedActivitiesByMemberId(@Param("memberId") UUID memberId);

    @Query("SELECT a FROM Attendance a WHERE a.activity.collectivity.id = :collectivityId")
    List<Attendance> findByCollectivityId(@Param("collectivityId") UUID collectivityId);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.activity.id = :activityId")
    Long countAttendancesByActivityId(@Param("activityId") UUID activityId);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.activity.id = :activityId AND a.present = true")
    Long countPresentByActivityId(@Param("activityId") UUID activityId);

    boolean existsByActivityIdAndMemberId(UUID activityId, UUID memberId);
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.member.id = :memberId")
    Long countByMemberId(@Param("memberId") UUID memberId);

    @Query("SELECT a.activity.id, COUNT(a) as total, SUM(CASE WHEN a.present = true THEN 1 ELSE 0 END) as present FROM Attendance a WHERE a.activity.collectivity.id = :collectivityId GROUP BY a.activity.id")
    List<Object[]> findAttendanceRatesByCollectivityId(@Param("collectivityId") UUID collectivityId);
}